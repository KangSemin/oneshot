package salute.oneshot.domain.cart.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemResponseDto {
    private final Long cartItemId;
    private final ProductResponseDto product;

    private final Integer quantity;

    public static CartItemResponseDto from(CartItem item) {
        return new CartItemResponseDto(
                item.getId(),
                ProductResponseDto.from(item.getProduct()),
                item.getQuantity()
        );
    }
}
