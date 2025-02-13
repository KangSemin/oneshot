package salute.oneshot.domain.cart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCartItemRequestDto {

    @NotNull
    private final Long productId;

    @NotNull
    private final Integer quantity;
}
