package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSDto {

    private Long userId;
    private Long orderId;

    public static GetOrderSDto of(Long userId, Long orderId) {
        return new GetOrderSDto(userId, orderId);
    }

}