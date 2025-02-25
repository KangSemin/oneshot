package salute.oneshot.domain.banner.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBannersSDto {

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Pageable pageable;

    public static GetBannersSDto of(
            String startDate,
            String endDate,
            Pageable pageable
    ) {
        return new GetBannersSDto(
                startDate != null ?
                        LocalDate.parse(startDate).atTime(LocalTime.MIN) : null,
                endDate != null ?
                        LocalDate.parse(endDate).atTime(LocalTime.MAX) : null,
                pageable);
    }
}
