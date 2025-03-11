package salute.oneshot.domain.event.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.Event;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventDetailResponseDto {

    private final Long id;
    private final String name;
    private final String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime endTime;

    private Object eventDetail;

    public static EventDetailResponseDto of(
            Event event,
            Object eventDetail
    ) {
        return new EventDetailResponseDto(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStartTime(),
                event.getEndTime(),
                eventDetail);
    }
}