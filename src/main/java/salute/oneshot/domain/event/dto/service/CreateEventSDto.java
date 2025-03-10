package salute.oneshot.domain.event.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEventSDto {

    private final String name;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final EventType eventType;
    private final int limitCount;
    private final Object eventDetailData;

    public static CreateEventSDto of(
            String name,
            String description,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            EventType eventType,
            int limitCount,
            Object eventDetailData
    ) {
        return new CreateEventSDto(
                name,
                description,
                DateTimeUtil.parseStartDateTime(
                        startDate,
                        startTime),
                DateTimeUtil.parseEndDateTime(
                        endDate,
                        endTime),
                eventType,
                limitCount,
                eventDetailData);
    }
}