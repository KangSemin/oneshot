package salute.oneshot.domain.delivery.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.delivery.enums.CourierCompany;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryRequestDto {

    private Long orderId;
    private final String receiverName;
    private final String receiverPhone;
    private final String deliveryMessage;
    private final String trackingNumber;
    private final String courierCompany;

    public static DeliveryRequestDto of(
            Long orderId,
            String receiverNave,
            String receiverPhone,
            String deliveryMessage,
            String trackingNumber,
            String courierCompany
    ) {
        return new DeliveryRequestDto(
                orderId,
                receiverNave,
                receiverPhone,
                deliveryMessage,
                trackingNumber,
                courierCompany);
    }
}
