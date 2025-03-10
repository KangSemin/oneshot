package salute.oneshot.domain.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import salute.oneshot.domain.event.entity.EventResult;
import salute.oneshot.domain.event.entity.EventStatus;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventNotificationService {

    private final ConcurrentHashMap<Long, List<SseEmitter>> eventEmitters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, SseEmitter> userEmitters = new ConcurrentHashMap<>();

    private static final long TIMEOUT_DURATION = 1800000L; // 30분
    private static final String EVENT_NAME_STATUS = "eventStatus";
    private static final String EVENT_NAME_RESULT = "eventResult";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_RESULT = "result";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIMESTAMP = "timestamp";

    // SSE 구독 로직
    public SseEmitter subscribeEvent(Long eventId, EventStatus currentStatus) {
        SseEmitter emitter = new SseEmitter(TIMEOUT_DURATION);
        eventEmitters.computeIfAbsent(eventId, k ->
                new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitter(eventId, emitter));
        emitter.onTimeout(() -> removeEmitter(eventId, emitter));
        emitter.onError(e -> removeEmitter(eventId, emitter));

        try {
            boolean isActive = (currentStatus == EventStatus.ONGOING);
            emitter.send(SseEmitter.event()
                    .name(EVENT_NAME_STATUS)
                    .data(Map.of(KEY_ACTIVE, isActive)));
        } catch (IOException e) {
            removeEmitter(eventId, emitter);
        }

        return emitter;
    }

    private void removeEmitter(Long eventId, SseEmitter emitter) {
        eventEmitters.computeIfPresent(eventId, (key, emitters) -> {
            emitters.removeIf(e -> e == emitter);
            return emitters.isEmpty() ? null : emitters;
        });
    }

    // 이벤트 상태 변경 알림
    @Async
    public void notifyEventStatusChange(Long eventId, boolean isActive) {
        eventEmitters.computeIfPresent(eventId, (key, emitters) -> {
            Set<SseEmitter> failedEmitters = new HashSet<>();

            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(SseEmitter.event()
                            .name(EVENT_NAME_STATUS)
                            .data(Map.of(KEY_ACTIVE, isActive)));
                } catch (IOException e) {
                    failedEmitters.add(emitter);
                }
            }
            emitters.removeAll(failedEmitters);

            return emitters.isEmpty() ? null : emitters;
        });
    }

    public void sendEventResult(Long userId, EventResult result) {
        SseEmitter emitter = userEmitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name(EVENT_NAME_RESULT)
                        .data(Map.of(
                                KEY_USER_ID, userId,
                                KEY_RESULT, result.name(),
                                KEY_MESSAGE, result.getMessage(),
                                KEY_TIMESTAMP, System.currentTimeMillis())));

                // 결과 전송 후 즉시 연결 종료
                emitter.complete();
                userEmitters.remove(userId);
            } catch (IOException e) {
                log.error("SSE 알림 전송 실패 - userId: {}", userId, e);
                emitter.complete();
                userEmitters.remove(userId);
            }
        }
    }
}