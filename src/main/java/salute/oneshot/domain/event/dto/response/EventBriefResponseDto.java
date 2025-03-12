package salute.oneshot.domain.event.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.event.entity.Event;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventBriefResponseDto {

    private final Long id;
    private final String name;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime endTime;

    public static EventBriefResponseDto from(Event event) {
        return new EventBriefResponseDto(
                event.getId(),
                event.getName(),
                event.getStartTime(),
                event.getEndTime());
    }
}

