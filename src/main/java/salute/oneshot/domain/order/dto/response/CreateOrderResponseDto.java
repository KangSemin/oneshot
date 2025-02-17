package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderResponseDto {

    private final Long orderId;
    private final String name;
    private final Long amount;
    private final OrderStatus status;
    private final LocalDateTime orderDate;

    public static CreateOrderResponseDto from(Order order) {
        return new CreateOrderResponseDto(order.getId(), order.getName(),
                order.getAmount(), order.getStatus(), order.getCreatedAt());
    }
}