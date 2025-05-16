package salute.oneshot.domain.payment.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentRequestDto {
    // TossPayment의 orderId -> Payment의 orderNumber
    private final String orderId;
    private final Long amount;
    private final String paymentKey;
}
