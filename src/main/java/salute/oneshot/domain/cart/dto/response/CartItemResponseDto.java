package salute.oneshot.domain.cart.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.entity.CartItem;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemResponseDto {
    private final Long id;
    private final Long productId;
    private final Integer amount;

    public static CartItemResponseDto from(CartItem item) {
        return new CartItemResponseDto(
                item.getId(),
                item.getProduct().getId(),
                item.getAmount()
        );
    }
}
