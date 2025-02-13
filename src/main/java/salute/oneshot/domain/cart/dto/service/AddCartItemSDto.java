package salute.oneshot.domain.cart.dto.service;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.dto.CartValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddCartItemSDto {
    private final Long userId;
    private final Long productId;

    @Size(
            max = CartValidationConst.QUANTITY_MAX,
            min = CartValidationConst.QUANTITY_MIN,
            message = CartValidationConst.QUANTITY_RANGE_MESSAGE
    )
    private final Integer quantity;

    public static AddCartItemSDto of(Long userId, Long productId, Integer quantity) {
        return new AddCartItemSDto(userId, productId, quantity);
    }
}
