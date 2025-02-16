package salute.oneshot.domain.shipping.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.entity.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserShippingResponseDto {
    private Long id;
    //private String orderNumber;
    private String courierName;
    private String trackingNumber;
    private ShippingStatus status;
    private String deliveryMessage;

    public static UserShippingResponseDto from(Shipping shipping) {
        return new UserShippingResponseDto(
                shipping.getId(),
                //shipping.getOrder().getOrderNumber(),
                shipping.getCourierCompany().getCompanyName(),
                shipping.getTrackingNumber(),
                shipping.getStatus(),
                shipping.getDeliveryMessage());
    }
}
