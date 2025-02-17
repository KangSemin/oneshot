package salute.oneshot.domain.delivery.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDeliverySDto {

    private final Long userId;
    private final Long orderId;

    public static UserDeliverySDto of(Long userId, Long orderId) {
        return new UserDeliverySDto(userId, orderId);
    }
}
