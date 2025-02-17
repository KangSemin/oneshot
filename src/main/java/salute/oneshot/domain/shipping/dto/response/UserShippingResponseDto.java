package salute.oneshot.domain.shipping.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.enums.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserShippingResponseDto {
    private final Long shippingId;
    private final Long orderId;
    private final String courierName;
    private final String trackingNumber;
    private final ShippingStatus status;
    private final String deliveryMessage;

    public static UserShippingResponseDto from(Shipping shipping) {
        return new UserShippingResponseDto(
                shipping.getId(),
                shipping.getOrder().getId(),
                shipping.getCourierCompany().getCompanyName(),
                shipping.getTrackingNumber(),
                shipping.getStatus(),
                shipping.getDeliveryMessage());
    }
}
