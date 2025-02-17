package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteOrderSDto {

    private Long userId;
    private Long orderId;

    public static DeleteOrderSDto of(Long userId, Long orderId) {
        return new DeleteOrderSDto(userId, orderId);
    }
}