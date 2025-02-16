package salute.oneshot.domain.shipping.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.shipping.enums.CourierCompany;

@Entity
@Table(name = "shippings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @OneToOne(mappedBy = "shippings")
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
}
