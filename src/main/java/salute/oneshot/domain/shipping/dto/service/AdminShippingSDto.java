package salute.oneshot.domain.shipping.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.shipping.enums.ShippingStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminShippingSDto {

    private final Long shippingId;
    private final ShippingStatus status;

    public static AdminShippingSDto of(
            Long shippingId,
            ShippingStatus status
    ) {
        return new AdminShippingSDto(shippingId, status);
    }
}
