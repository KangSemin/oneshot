package salute.oneshot.domain.event.procssor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.dto.FreeEventData;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.global.exception.InvalidException;

@Slf4j
@Component
@RequiredArgsConstructor
public class FreeEventProcessor implements EventProcessor{

    private final ObjectMapper objectMapper;

    @Override
    public void processEvent(Event event, EventDetail detail) {

    }

    @Override
        public void validateDetails(Object detailJson) {
        FreeEventData data = parseDetailData(detailJson);

        if (data.getCoupons().isEmpty()) {
            throw new InvalidException(ErrorCode.MISSING_COUPON);
        }
        if (data.getCoupons().stream().anyMatch(coupon ->
                coupon.getCouponId() == null ||
                        coupon.getCouponName().isEmpty())) {
            throw new InvalidException(ErrorCode.MISSING_COUPON);
        }

        if (data.getLimitCount() <= 0)
            throw new InvalidException(ErrorCode.INVALID_LIMIT_COUNT);
    }

    @Override
    public FreeEventData parseDetailData(Object detailJson) {
        if (detailJson == null) {
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }

        try {
            if (detailJson instanceof String) {
                return objectMapper.readValue((String) detailJson, FreeEventData.class);
            }
            return objectMapper.convertValue(detailJson, FreeEventData.class);
        } catch (Exception e) {
            log.error("Free event JSON 파싱 실패: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }

    @Override
    public String convertToJson(Object data) {
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
}