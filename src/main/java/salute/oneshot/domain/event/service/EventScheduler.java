package salute.oneshot.domain.event.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.domain.event.repository.EventRepository;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final EventService eventService;
    private final EventCompletionService eventCompletionService;
    private final EventRepository eventRepository;
    private final TaskScheduler taskScheduler;
    private final ExecutorService eventExecutor = Executors.newFixedThreadPool(10);
    private final static ZoneId KOREA_ZONE = ZoneId.of("Asia/Seoul");

    @PostConstruct
    public void initScheduleOnStartup() {
        scheduleUpcomingEvents();
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 실행
    public void scheduleUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now(KOREA_ZONE);
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<Event> upcomingEvents = eventRepository
                .findEventsToStart(startOfDay, endOfDay, EventType.FCFS, EventStatus.UPCOMING);

        upcomingEvents.forEach(event -> {
            Instant eventInstant = event.getStartTime().atZone(KOREA_ZONE).toInstant();
            Instant nowInstant = ZonedDateTime.now(KOREA_ZONE).toInstant();

            if (eventInstant.isBefore(nowInstant)) {
                // 놓친 이벤트 비동기 실행 (커스텀 스레드 풀 사용)
                CompletableFuture.runAsync(() -> retryEventExecution(event), eventExecutor)
                        .exceptionally(ex -> {
                            log.error("이벤트 {} 비동기 실행 중 오류: {}", event.getId(), ex.getMessage());
                            return null;
                        });
            } else {
                // 정상 예약된 이벤트 스케줄링
                taskScheduler.schedule(() -> eventService.activateEvent(event.getId()), eventInstant);
            }
        });
    }

    private void retryEventExecution(Event event) {
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = Map.of(
                SocketTimeoutException.class, true,  // 네트워크 문제: 재시도
                IOException.class, true,  // 입출력 예외: 재시도
                IllegalArgumentException.class, false  // 비즈니스 예외: 재시도하지 않음
        );

        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy =
                new SimpleRetryPolicy(3, retryableExceptions);
        retryTemplate.setRetryPolicy(retryPolicy);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(5000); // 5초 대기
        backOffPolicy.setMultiplier(2.0); // 2배씩 증가
        retryTemplate.setBackOffPolicy(backOffPolicy);

        try {
            retryTemplate.execute(context -> {
                log.info("이벤트 {} 실행 시도 {}/{}", event.getId(), context.getRetryCount() + 1, 3);
                eventService.activateEvent(event.getId());
                log.info("이벤트 {} 실행 성공!", event.getId());
                return null;
            });
        } catch (Exception e) {
            log.error("이벤트 {} 최종 실행 실패: {}", event.getId(), e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 종료된 이벤트 처리
    public void handleCompletedEvents() {
        LocalDateTime now = LocalDateTime.now(KOREA_ZONE);

        List<Event> eventsToComplete =
                eventRepository.findEventsToEnd(now);

        for (Event event : eventsToComplete) {
            eventCompletionService.completeEvent(event.getId());
        }
    }
}