package salute.oneshot.domain.event.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.entity.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEventsSDto {

    private final EventStatus status;
    private final EventType type;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Pageable pageable;

    public static GetEventsSDto of(
            String status,
            String type,
            String startDate,
            String endDate,
            Pageable pageable
    ) {
        return new GetEventsSDto(
                status != null ?
                        EventStatus.of(status) : null,
                type != null ?
                        EventType.of(type) : null,
                startDate != null ?
                        LocalDate.parse(startDate).atTime(LocalTime.MIN) : null,
                endDate != null ?
                        LocalDate.parse(endDate).atTime(LocalTime.MAX) : null,
                pageable);
    }
}
