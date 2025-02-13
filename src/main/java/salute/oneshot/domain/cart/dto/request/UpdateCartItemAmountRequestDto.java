package salute.oneshot.domain.cart.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.dto.CartValidationConst;

@Getter
@AllArgsConstructor
public class UpdateCartItemAmountRequestDto {

    @Size(
            max = CartValidationConst.QUANTITY_MAX,
            min = CartValidationConst.QUANTITY_MIN,
            message = CartValidationConst.QUANTITY_RANGE_MESSAGE
    )
    private final Integer quantity;
}
