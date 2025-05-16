package salute.oneshot.domain.event.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.EventResult;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipateEventDto {

    private final Long eventId;
    private final Long userId;
    private final Long couponId;
    private final EventResult eventResult;

    public static ParticipateEventDto of(
            Long eventId,
            Long userId,
            Long couponId,
            EventResult eventResult
    ) {
        return new ParticipateEventDto(
                eventId,
                userId,
                couponId,
                eventResult);
    }
}
