package salute.oneshot.domain.delivery.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.enums.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDeliveryResponseDto {
    private final Long deliveryId;
    private final Long orderId;
    private final String courierName;
    private final String trackingNumber;
    private final ShippingStatus status;
    private final String deliveryMessage;

    public static UserDeliveryResponseDto from(Delivery shipping) {
        return new UserDeliveryResponseDto(
                shipping.getId(),
                shipping.getOrder().getId(),
                shipping.getCourierCompany().getCompanyName(),
                shipping.getTrackingNumber(),
                shipping.getStatus(),
                shipping.getDeliveryMessage());
    }
}
