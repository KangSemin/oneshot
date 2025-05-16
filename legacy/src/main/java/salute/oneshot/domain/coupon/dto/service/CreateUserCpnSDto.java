package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserCpnSDto {

    private final Long couponId;
    private final Long userId;

    public static CreateUserCpnSDto of(
            Long couponId,
            Long userId
    ) {
        return new CreateUserCpnSDto(
                couponId,
                userId);
    }
}