package salute.oneshot.domain.shipping.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.enums.CourierCompany;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShippingRequestDto {

    private final String receiverName;
    private final String receiverPhone;
    private final String deliveryMessage;
    private final String trackingNumber;
    private final CourierCompany courierCompany;
}
