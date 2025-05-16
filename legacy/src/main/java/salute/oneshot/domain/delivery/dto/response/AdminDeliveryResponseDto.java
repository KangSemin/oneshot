package salute.oneshot.domain.delivery.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.enums.ShippingStatus;
import salute.oneshot.domain.delivery.enums.CourierCompany;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDeliveryResponseDto {
    private final Long deliveryId;
    private final Long orderId;
    private final String receiverName;
    private final String receiverPhone;
    private final String deliveryMessage;
    private final CourierCompany courierCompany;
    private final String trackingNumber;
    private final ShippingStatus status;
    private final LocalDateTime createdAt;

    public static AdminDeliveryResponseDto from(Delivery shipping) {
        return new AdminDeliveryResponseDto(
                shipping.getId(),
                shipping.getOrder().getId(),
                shipping.getReceiverName(),
                shipping.getReceiverPhone(),
                shipping.getDeliveryMessage(),
                shipping.getCourierCompany(),
                shipping.getTrackingNumber(),
                shipping.getStatus(),
                shipping.getCreatedAt());
    }
}
