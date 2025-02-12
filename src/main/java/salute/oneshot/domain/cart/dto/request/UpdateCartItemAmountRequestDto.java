package salute.oneshot.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCartItemAmountRequestDto {
    private final Integer quantity;
}
