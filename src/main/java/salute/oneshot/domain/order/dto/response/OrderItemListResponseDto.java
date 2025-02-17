package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.OrderItem;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItemListResponseDto {

    private String productName;
    private int quantity;
    private int price;

    public static OrderItemListResponseDto from(OrderItem orderItem) {
        return new OrderItemListResponseDto(orderItem.getProduct().getName(),
                orderItem.getQuantity(), orderItem.getPrice());
    }

}