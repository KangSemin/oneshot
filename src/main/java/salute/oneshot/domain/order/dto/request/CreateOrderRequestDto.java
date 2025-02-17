package salute.oneshot.domain.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDto {

    private Long addressId;

}