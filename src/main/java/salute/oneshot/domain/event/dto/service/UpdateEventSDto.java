package salute.oneshot.domain.event.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateEventSDto {

    private final Long eventId;
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final EventType eventType;
    private final Object eventDetail;

    public static UpdateEventSDto of(
            Long eventId,
            String name,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            EventType eventType,
            Object eventDetail
    ) {
        return new UpdateEventSDto(
                eventId,
                name,
                DateTimeUtil.parseStartDateTime(
                        startDate,
                        startTime),
                DateTimeUtil.parseEndDateTime(
                        endDate,
                        endTime),
                eventType,
                eventDetail);
    }
}
