package salute.oneshot.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnBriefResponseDto {

    private Long userCouponId;
    private Long userId;
    private CpnBriefResponseDto coupon;
    private UserCouponStatus status;

    public static UserCpnBriefResponseDto from(UserCoupon userCoupon) {
        return new UserCpnBriefResponseDto(
                userCoupon.getId(),
                userCoupon.getUser().getId(),
                CpnBriefResponseDto.from(userCoupon.getCoupon()),
                userCoupon.getStatus());
    }
}
