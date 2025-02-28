package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderSDto {

    private Long userId;
    private Long orderId;
    private String orderStatus;

    public static UpdateOrderSDto of(Long userId, Long orderId, String orderStatus) {
        return new UpdateOrderSDto(userId, orderId, orderStatus);
    }

}