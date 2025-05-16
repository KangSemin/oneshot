package salute.oneshot.domain.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String orderNumber;
    private String paymentKey;
    private PaymentStatus status;
    private String orderName;
    private Long totalAmount;

    public Payment(String orderNumber, String paymentKey, PaymentStatus status, String orderName, Long totalAmount) {
        this.orderNumber = orderNumber;
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
