package salute.oneshot.domain.delivery.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.delivery.enums.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDeliverySDto {

    private final Long deliveryId;
    private final ShippingStatus status;

    public static AdminDeliverySDto of(
            Long deliveryId,
            ShippingStatus status
    ) {
        return new AdminDeliverySDto(deliveryId, status);
    }
}
