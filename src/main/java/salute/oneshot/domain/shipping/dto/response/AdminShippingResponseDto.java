package salute.oneshot.domain.shipping.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.entity.ShippingStatus;
import salute.oneshot.domain.shipping.enums.CourierCompany;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminShippingResponseDto {
    private Long id;
    //private String orderNumber;
    private String receiverName;
    private String receiverPhone;
    private String deliveryMessage;
    private CourierCompany courierCompany;
    private String trackingNumber;
    private ShippingStatus status;
    private LocalDateTime createdAt;

    public static AdminShippingResponseDto from(Shipping shipping) {
        return new AdminShippingResponseDto(
                shipping.getId(),
                //shipping.getOrder().getOrderNumber(),
                shipping.getReceiverName(),
                shipping.getReceiverPhone(),
                shipping.getDeliveryMessage(),
                shipping.getCourierCompany(),
                shipping.getTrackingNumber(),
                shipping.getStatus(),
                shipping.getCreatedAt());
    }
}
