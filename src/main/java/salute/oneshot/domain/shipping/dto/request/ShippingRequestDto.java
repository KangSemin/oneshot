package salute.oneshot.domain.shipping.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.enums.CourierCompany;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShippingRequestDto {

    private String receiverName;
    private String receiverPhone;
    private String deliveryMessage;
    private String trackingNumber;
    private CourierCompany courierCompany;
}
