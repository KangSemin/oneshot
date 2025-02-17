package salute.oneshot.domain.shipping.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.shipping.enums.CourierCompany;
import salute.oneshot.domain.shipping.enums.ShippingStatus;
import salute.oneshot.global.exception.InvalidException;

@Entity
@Table(name = "shippings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String receiverName;
    private String receiverPhone;
    private String deliveryMessage;
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private CourierCompany courierCompany;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status = ShippingStatus.REGISTERED;

    private Shipping(
            Order order,
            String receiverName,
            String receiverPhone,
            String deliveryMessage,
            String trackingNumber,
            CourierCompany courierCompany
    ) {
        this.order = order;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.deliveryMessage = deliveryMessage;
        this.trackingNumber = trackingNumber;
        this.courierCompany = courierCompany;
    }

    public static Shipping of(
            Order order,
            String receiverName,
            String receiverPhone,
            String deliveryMessage,
            String trackingNumber,
            CourierCompany courierCompany
    ) {
        return new Shipping(
                order,
                receiverName,
                receiverPhone,
                deliveryMessage,
                trackingNumber,
                courierCompany
        );
    }

    public void updateStatus(ShippingStatus status) {
        if (this.status == status) {
            throw new InvalidException(ErrorCode.SAME_STATUS_UPDATE);
        }

        if (!this.status.canTransitionTo(status)) {
            throw new InvalidException(ErrorCode.INVALID_STATUS_CHANGE);
        }
    }
}
