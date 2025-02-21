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

    public static TossConfirmPaymentRequestDto of(String orderId, Long amount, String paymentKey) {
        return new TossConfirmPaymentRequestDto(
                orderId,
                amount,
                paymentKey
        );
    }
}
