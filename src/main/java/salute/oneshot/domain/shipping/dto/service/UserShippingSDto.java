package salute.oneshot.domain.shipping.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserShippingSDto {

    private final Long userId;
    private final Long orderId;

    public static UserShippingSDto of(Long userId, Long orderId) {
        return new UserShippingSDto(userId, orderId);
    }
}
