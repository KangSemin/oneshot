package salute.oneshot.domain.cart.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.entity.Cart;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartResponseDto {

    private final List<CartItemResponseDto> cartItemList;

    public static CartResponseDto from(Cart cart) {
        return new CartResponseDto(cart.getCartItemList()
                .stream()
                .map(CartItemResponseDto::from)
                .toList());
    }

    public static CartResponseDto empty(Long userId) {
        return new CartResponseDto(new ArrayList<>());
    }
}
