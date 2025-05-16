package salute.oneshot.domain.coupon.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum UserCouponStatus {
    ISSUED, USED, EXPIRED;

    public static UserCouponStatus of(String status) {
        return Arrays.stream(UserCouponStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_COUPON_STATUS));
    }
}
