package salute.oneshot.domain.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String orderId;
    private String paymentKey;
    private PaymentStatus status;
    private String orderName;
    private Long totalAmount;

    public Payment(String orderId, String paymentKey, PaymentStatus status, String orderName, Long totalAmount) {
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.status = status;
        this.orderName = orderName;
        this.totalAmount = totalAmount;
    }

    public static Payment fromDto(TossPayment tossPayment) {
        return new Payment(
                tossPayment.getOrderId(),
                tossPayment.getPaymentKey(),
                tossPayment.getStatus(),
                tossPayment.getOrderName(),
                tossPayment.getTotalAmount()
        );
    }

    public void updateStatus(PaymentStatus status) {
        this.status = status;
    }
}
