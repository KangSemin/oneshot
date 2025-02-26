package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCpnSDto {

    private final LocalDateTime starTime;
    private final LocalDateTime endTime;
    private final Pageable pageable;

    public static GetCpnSDto of(
            String starTime,
            String endTime,
            Pageable pageable
    ) {
        return new GetCpnSDto(
                starTime != null ?
                        LocalDate.parse(starTime).atTime(LocalTime.MIN) : null,
                endTime != null ?
                        LocalDate.parse(endTime).atTime(LocalTime.MAX) : null,
                pageable);
    }
}
