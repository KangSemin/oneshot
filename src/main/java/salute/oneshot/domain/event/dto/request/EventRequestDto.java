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
    private final EventType eventType;
    private final int limitCount;
    private final Object eventDetailData;
}