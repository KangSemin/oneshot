package salute.oneshot.domain.payment.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentRequestDto {
    private final String orderId;
    private final int amount;
    private final String paymentKey;
}
