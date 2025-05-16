package salute.oneshot.domain.event.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipateEventSDto {

    private final Long eventId;
    private final Long userId;

    public static ParticipateEventSDto of(Long eventId, Long userId) {
        return new ParticipateEventSDto(eventId, userId);
    }
}
