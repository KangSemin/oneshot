package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserCpnSDto {

    private final Long userId;
    private final UserCouponStatus status;
    private final Pageable pageable;

    public static GetUserCpnSDto of(
            Long userId,
            String status,
            Pageable pageable
    ) {
        return new GetUserCpnSDto(
                userId,
                status != null ? UserCouponStatus.of(status) : null,
                pageable);
    }
}
