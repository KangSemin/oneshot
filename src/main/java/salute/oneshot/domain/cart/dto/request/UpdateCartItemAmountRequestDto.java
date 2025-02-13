package salute.oneshot.domain.cart.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.common.dto.error.ErrorCode;

@Getter
@AllArgsConstructor
public class UpdateCartItemAmountRequestDto {


    @Max(value = 10)
    @NotNull
    private final Integer quantity;
}
