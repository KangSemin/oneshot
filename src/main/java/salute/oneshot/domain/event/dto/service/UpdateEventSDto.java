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
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final EventType eventType;
    private final Object eventDetailData;

    public static UpdateEventSDto of(
            Long eventId,
            String name,
            String description,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            EventType eventType,
            Object eventDetailData
    ) {
        return new UpdateEventSDto(
                eventId,
                name,
                description,
                DateTimeUtil.parseStartDateTime(
                        startDate,
                        startTime),
                DateTimeUtil.parseEndDateTime(
                        endDate,
                        endTime),
                eventType,
                eventDetailData);
    }
}
