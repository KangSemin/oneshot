package salute.oneshot.domain.coupon.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CpnRequestDto {

    private final String couponName;
    private final int discountValue;
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
}
