package salute.oneshot.domain.payment.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.entity.PaymentStatus;
import salute.oneshot.domain.payment.entity.TossPayment;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentResponseDto {

    private final String paymentKey;
    private final PaymentStatus status;
    private final String orderId;
    private final String orderName;
    private final Long totalAmount;

    public static PaymentResponseDto from(TossPayment tossPayment) {
        return new PaymentResponseDto(
                tossPayment.getPaymentKey(),
                tossPayment.getStatus(),
                tossPayment.getOrderId(),
                tossPayment.getOrderName(),
                tossPayment.getTotalAmount()
        );
    }

    public static PaymentResponseDto from(Payment payment) {
        return new PaymentResponseDto(
                payment.getPaymentKey(),
                payment.getStatus(),
                payment.getOrderNumber(),
                payment.getOrderName(),
                payment.getTotalAmount()
        );
    }
}
