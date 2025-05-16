package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderResponseDto {

    private Long orderId;
    private String name;
    private Long amount;
    private OrderStatus status;
    private LocalDateTime updateDate;

    public static UpdateOrderResponseDto from(Order order) {
        return new UpdateOrderResponseDto(order.getId(), order.getName(),
                order.getAmount(), order.getStatus(), order.getModifiedAt());
    }
}