package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.Order;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderResponseDto {

    private final Long orderId;
    private final Long amount;
    private final List<OrderItemListResponseDto> orderItems;

    public static GetOrderResponseDto from(Order order, List<OrderItemListResponseDto> orderItems) {
        return new GetOrderResponseDto(order.getId(), order.getAmount(),orderItems);
    }
}