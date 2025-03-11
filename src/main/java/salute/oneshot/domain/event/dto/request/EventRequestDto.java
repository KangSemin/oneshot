package salute.oneshot.domain.event.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.EventType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventRequestDto {

    private final String name;
    private final String description;
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
    private final String eventType;
    private final int limitCount;
    private final Object eventDetailData;

    public static EventRequestDto of(
            String name,
            String description,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            String eventType,
            int limitCount,
            Object eventDetailData
    ) {
        return new EventRequestDto(
                name,
                description,
                startDate,
                startTime,
                endDate,
                endTime,
                eventType,
                limitCount,
                eventDetailData);
    }
}