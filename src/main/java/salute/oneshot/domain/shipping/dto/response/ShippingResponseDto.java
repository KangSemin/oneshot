package salute.oneshot.domain.shipping.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.entity.ShippingStatus;
import salute.oneshot.domain.shipping.enums.CourierCompany;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShippingResponseDto {

    private Long id;
    //private String receiverName;
    private String receiverPhone;
    private String deliveryMessage;
    private CourierCompany courierCompany;
    private String trackingNumber;
    private ShippingStatus status;

    public static ShippingResponseDto from(Shipping shipping) {
        return new ShippingResponseDto(
                shipping.getId(),
                shipping.getReceiverPhone(),
                shipping.getDeliveryMessage(),
                shipping.getCourierCompany(),
                shipping.getTrackingNumber(),
                shipping.getStatus());
    }
}
