package salute.oneshot.domain.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.order.entity.Order;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    // TODO: fetch type 고민
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    public static Payment from(Order order) {
        return new Payment(
                order,
                order.getAmount()
        );
    }

    public Payment(Order order, Long amount) {
        this.order = order;
        this.amount = amount;
    }

    public void updateStatus(PaymentStatus paymentStatus) {
        this.status = paymentStatus;
    }
}
