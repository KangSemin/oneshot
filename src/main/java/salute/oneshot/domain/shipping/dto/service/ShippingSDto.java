package salute.oneshot.domain.shipping.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.enums.CourierCompany;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShippingSDto {

    private final Long orderId;
    private final String receiverName;
    private final String receiverPhone;
    private final String deliveryMessage;
    private final String trackingNumber;
    private final CourierCompany courierCompany;

    public static ShippingSDto of(
            Long orderId,
            String receiverName,
            String receiverPhone,
            String deliveryMessage,
            String trackingNumber,
            CourierCompany courierCompany
    ){
        return new ShippingSDto(
                orderId,
                receiverName,
                receiverPhone,
                deliveryMessage,
                trackingNumber,
                courierCompany
        );
    }
}
