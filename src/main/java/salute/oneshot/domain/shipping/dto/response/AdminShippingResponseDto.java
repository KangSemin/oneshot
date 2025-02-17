package salute.oneshot.domain.shipping.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.enums.ShippingStatus;
import salute.oneshot.domain.shipping.enums.CourierCompany;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminShippingResponseDto {
    private final Long shippingId;
    private final Long orderId;
    private final String receiverName;
    private final String receiverPhone;
    private final String deliveryMessage;
    private final CourierCompany courierCompany;
    private final String trackingNumber;
    private final ShippingStatus status;
    private final LocalDateTime createdAt;

    public static AdminShippingResponseDto from(Shipping shipping) {
        return new AdminShippingResponseDto(
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
