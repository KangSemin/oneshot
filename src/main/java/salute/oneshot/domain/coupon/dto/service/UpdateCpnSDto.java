package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCpnSDto {

    private final Long couponId;
    private final String couponName;
    private final int discountValue;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public static UpdateCpnSDto of(
            Long couponId,
            CpnRequestDto requestDto
    ) {
        return new UpdateCpnSDto(
                couponId,
                requestDto.getCouponName(),
                requestDto.getDiscountValue(),
                DateTimeUtil.parseStartDateTime(
                        requestDto.getStartDate(),
                        requestDto.getStartTime()),
                DateTimeUtil.parseEndDateTime(
                        requestDto.getEndDate(),
                        requestDto.getEndTime()));
    }
}
