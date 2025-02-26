package salute.oneshot.domain.coupon.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.dto.request.UserCpnRequestDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserCpnSDto {

    private final Long couponId;
    private final Long userId;

    public static CreateUserCpnSDto of(
            Long couponId,
            UserCpnRequestDto requestDto
    ) {
        return new CreateUserCpnSDto(
                couponId,
                requestDto.getUserId());
    }
}
