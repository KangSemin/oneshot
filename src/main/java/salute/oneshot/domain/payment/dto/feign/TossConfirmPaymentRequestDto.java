package salute.oneshot.domain.payment.dto.feign;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossConfirmPaymentRequestDto {

    private final String orderId;
    private final Long amount;
    private final String paymentKey;

    public static TossConfirmPaymentRequestDto of(String orderNumber, Long amount, String paymentKey) {
        return new TossConfirmPaymentRequestDto(
                orderNumber,
                amount,
                paymentKey
        );
    }
}
