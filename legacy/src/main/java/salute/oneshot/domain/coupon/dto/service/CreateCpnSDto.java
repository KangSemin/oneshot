package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCpnSDto {

    private final String couponName;
    private final int discountValue;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public static CreateCpnSDto of(
            String couponName,
            int discountValue,
            String startDate,
            String startTime,
            String endDate,
            String endTime
    ) {
        return new CreateCpnSDto(
                couponName,
                discountValue,
                DateTimeUtil.parseStartDateTime(
                                startDate,
                                startTime),
                DateTimeUtil.parseEndDateTime(
                        endDate,
                        endTime));
    }
}
