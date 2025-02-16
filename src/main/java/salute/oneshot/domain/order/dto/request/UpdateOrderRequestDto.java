package salute.oneshot.domain.order.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateOrderRequestDto {

    @NotBlank
    private String orderStatus;

}