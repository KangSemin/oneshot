package salute.oneshot.domain.coupon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.entity.CouponValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnRequestDto {

    @NotNull(message = CouponValidationConst.USER_ID_MESSAGE)
    private Long userId;

    public static UserCpnRequestDto of(Long userId) {
        return new UserCpnRequestDto(userId);
    }
}
