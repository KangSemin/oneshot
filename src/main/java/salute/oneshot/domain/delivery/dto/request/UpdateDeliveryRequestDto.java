package salute.oneshot.domain.delivery.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.delivery.enums.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateDeliveryRequestDto {

    private final ShippingStatus status;
}
