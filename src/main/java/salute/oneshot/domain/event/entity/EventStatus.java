package salute.oneshot.domain.event.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum EventStatus {
    UPCOMING, ONGOING, ENDED;

    public static EventStatus of(String status) {
        return Arrays.stream(EventStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_EVENT_STATUS));
    }
}
