package salute.oneshot.domain.event.procssor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.coupon.repository.RedisEventCouponRepository;
import salute.oneshot.domain.event.dto.FreeEventData;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventResult;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.domain.event.service.EventCompletionService;
import salute.oneshot.domain.event.service.EventNotificationService;
import salute.oneshot.domain.event.service.EventProducerService;
import salute.oneshot.global.exception.InvalidException;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class FreeEventProcessor implements EventProcessor {

    private final EventProducerService eventProducerService;
    private final EventCompletionService eventCompletionService;
    private final EventNotificationService eventNotificationService;
    private final RedisEventCouponRepository redisEventCouponRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public EventDetail processEvent(Event event, Object eventDetailData) {
        validateEventDetail(eventDetailData);
        String eventDetailJson =
                convertEventDetailToJson(eventDetailData);

        return EventDetail.of(event, eventDetailJson);
    }

    @Override
    public ParticipateEventDto participateEvent(
            Long eventId,
            Long userId,
            String eventDetailJson,
            int limitCount
    ) {
        FreeEventData eventData = parseEventDetail(eventDetailJson);
        ParticipateEventDto result = eventProducerService.processEventParticipation(
                eventId, userId.toString(), eventData.getCouponId(), String.valueOf(limitCount));

        eventNotificationService.sendEventResult(
                result.getUserId(),
                result.getEventResult());

        if (result.getEventResult() == EventResult.WINNER) {
            eventProducerService.sendToEventQueue(result);
        }

        if (shouldCompleteEvent(eventId, limitCount)) {
            log.info("이벤트 {} 선착순 마감 감지", eventId);

            CompletableFuture.runAsync(() -> {
                eventCompletionService.completeEvent(eventId);
            });
        }

        return result;
    }

    @Override
    public void validateEventDetail(Object eventDetailData) {
        FreeEventData data = parseEventDetail(eventDetailData);

        if (data.getCouponId() == null || data.getCouponName().isEmpty()) {
            throw new InvalidException(ErrorCode.MISSING_COUPON);
        }

//        if (!redisEventCouponRepository
//                .isValidCouponId(String.valueOf(data.getCouponId()))
//        ) {
//            throw new InvalidException(ErrorCode.COUPON_NOT_FOUND);
//        }
    }

    @Override
    public FreeEventData parseEventDetail(Object eventDetailData) {
        if (eventDetailData == null) {
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }

        try {
            if (eventDetailData instanceof String) {
                return objectMapper.readValue((String) eventDetailData, FreeEventData.class);
            }
            return objectMapper.convertValue(eventDetailData, FreeEventData.class);
        } catch (Exception e) {
            log.error("Free event JSON 파싱 실패: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }

    @Override
    public String convertEventDetailToJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("Free event DTO 파싱 실패: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }

    @Override
    public EventType getSupportedEventType() {
        return EventType.FCFS;
    }

    private boolean shouldCompleteEvent(Long eventId, int limitCount) {
        String counterKey = "event:" + eventId + ":counter";
        String value = redisTemplate.opsForValue().get(counterKey);

        if (value == null) {
            return false;
        }

        int currentCount = Integer.parseInt(value);
        return currentCount >= limitCount;
    }
}