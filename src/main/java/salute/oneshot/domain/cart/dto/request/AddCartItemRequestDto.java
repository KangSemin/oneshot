package salute.oneshot.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCartItemRequestDto {
    private final Long productId;
    private final Integer amount;
}
