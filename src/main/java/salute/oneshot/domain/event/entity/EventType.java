package salute.oneshot.domain.event.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum EventType {
    FCFS, VOTE;

    public static EventType of(String status) {
        return Arrays.stream(EventType.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_EVENT_TYPE));
    }
}