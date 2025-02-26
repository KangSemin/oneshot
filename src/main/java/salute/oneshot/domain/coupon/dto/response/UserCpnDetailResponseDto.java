package salute.oneshot.domain.coupon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnDetailResponseDto {

    private Long userCouponId;
    private Long userId;
    private CpnBriefResponseDto coupon;
    private UserCouponStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime usedTime;

    public static UserCpnDetailResponseDto of(UserCoupon userCoupon) {
        return new UserCpnDetailResponseDto(
                userCoupon.getId(),
                userCoupon.getUser().getId(),
                CpnBriefResponseDto.from(userCoupon.getCoupon()),
                userCoupon.getStatus(),
                userCoupon.getUsedTime());
    }
}
