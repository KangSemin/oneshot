package salute.oneshot.domain.coupon.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnRequestDto {

    private Long userId;

    public static UserCpnRequestDto of(Long userId) {
        return new UserCpnRequestDto(userId);
    }
}
