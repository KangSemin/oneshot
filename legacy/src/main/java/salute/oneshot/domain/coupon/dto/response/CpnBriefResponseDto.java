package salute.oneshot.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.entity.Coupon;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CpnBriefResponseDto {

    private Long id;
    private String couponName;
    private int discountValue;

    public static CpnBriefResponseDto from(Coupon coupon) {
        return new CpnBriefResponseDto(
                coupon.getId(),
                coupon.getCouponName(),
                coupon.getDiscountValue());
    }
}
