package salute.oneshot.domain.event.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.dto.request.EventRequestDto;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateEventSDto {

    private final Long eventId;
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final EventType eventType;
    private final Object eventDetailJson;

    public static UpdateEventSDto of(
            Long eventId,
            EventRequestDto requestDto
    ) {
        return new UpdateEventSDto(
                eventId,
                requestDto.getName(),
                DateTimeUtil.parseStartDateTime(
                        requestDto.getStartDate(),
                        requestDto.getStartTime()),
                DateTimeUtil.parseEndDateTime(
                        requestDto.getEndDate(),
                        requestDto.getEndTime()),
                requestDto.getEventType(),
                requestDto.getEventDetailJson());
    }
}
