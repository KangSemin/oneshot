package salute.oneshot.domain.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.order.entity.Order;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderDetailsResponseDto {

    private final Long orderId;

    // TODO: Validation 필요!
    private final Long amountValue;
    private final String orderNumber;
    private final String orderName;
    private final String customerEmail;
    private final String customerName;
    private final String customerKey;

    public static GetOrderDetailsResponseDto from(Order order) {
        return new GetOrderDetailsResponseDto(
                order.getId(),
                order.getAmount(),
                order.getOrderNumber(),
                order.getName(),
                order.getUser().getEmail(),
                order.getUser().getNickName(),
                // TODO: customerKey 로직 위치
                "User-"+ order.getUser().getId()
        );
    }
}