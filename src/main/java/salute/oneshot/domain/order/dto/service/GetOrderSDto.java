package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSDto {

    private final Long userId;
    private final Long orderId;

    public static GetOrderSDto of(Long userId, Long orderId) {
        return new GetOrderSDto(userId, orderId);
    }

}