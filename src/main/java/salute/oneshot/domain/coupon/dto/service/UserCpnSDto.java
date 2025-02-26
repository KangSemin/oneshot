package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnSDto {

    private final Long userId;
    private final Long userCouponId;

    public static UserCpnSDto of(Long userId, Long userCouponId) {
        return new UserCpnSDto(userId, userCouponId);
    }
}
