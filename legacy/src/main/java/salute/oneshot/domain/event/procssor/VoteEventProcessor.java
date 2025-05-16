package salute.oneshot.domain.event.procssor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.dto.VoteEventData;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.global.exception.InvalidException;

@Slf4j
@Component
@RequiredArgsConstructor
public class VoteEventProcessor implements EventProcessor {

    private final ObjectMapper objectMapper;

    @Override
    public EventDetail processEvent(Event event, Object eventDetailData) {
        return null;
    }

    @Override
    public ParticipateEventDto participateEvent(Long eventId, Long userId, String eventDetailJson, int limitCount) {
        return null;
    }

    @Override
    public void validateEventDetail(Object eventDetailData) {
        VoteEventData data = parseEventDetail(eventDetailData);

        if (data.getCoupons().size() != 2) {
            throw new InvalidException(ErrorCode.INVALID_COUPON_COUNT);
        }

        if (data.getCoupons().stream().anyMatch(coupon ->
                coupon.getCouponId() == null ||
                        coupon.getCouponName().isEmpty())) {
            throw new InvalidException(ErrorCode.MISSING_COUPON);
        }

        if (data.getCandidates().size() != 2) {
            throw new InvalidException(ErrorCode.INVALID_CANDIDATE_COUNT);
        }

        if (data.getCandidates().stream().anyMatch(candidate ->
                candidate.getCocktailId() == null ||
                        candidate.getUserId() == null ||
                        candidate.getName() == null ||
                        candidate.getVoteCount() < 0)) {
            throw new InvalidException(ErrorCode.INVALID_CANDIDATE);
        }
    }

    @Override
    public VoteEventData parseEventDetail(Object eventDetailData) {
        if (eventDetailData == null) {
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }

        try {
            return objectMapper.convertValue(eventDetailData, VoteEventData.class);
        } catch (Exception e) {
            log.error("Vote event JSON 파싱 실패: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }

    @Override
    public String convertEventDetailToJson(Object eventDetailData) {
        try {
            return objectMapper.writeValueAsString(eventDetailData);
        } catch (JsonProcessingException e) {
            log.error("Free event DTO 파싱 실패: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }

    @Override
    public EventType getSupportedEventType() {
        return EventType.VOTE;
    }
}