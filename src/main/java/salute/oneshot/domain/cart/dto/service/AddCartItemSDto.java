package salute.oneshot.domain.cart.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddCartItemSDto {
    private final Long userId;
    private final Long productId;
    private final Integer quantity;

    public static AddCartItemSDto of(Long userId, Long productId, Integer quantity) {
        return new AddCartItemSDto(userId, productId, quantity);
    }
}
