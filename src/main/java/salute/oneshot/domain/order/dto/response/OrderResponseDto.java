package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDto {

    private Long orderId;
    private String name;
    private Long amount;
    private OrderStatus status;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order.getId(), order.getName(),
                order.getAmount(), order.getStatus());
    }
}