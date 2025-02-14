package salute.oneshot.domain.payment.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentSDto {

    private final String orderId;
    private final Long amount;
    private final String paymentKey;

    public static ConfirmPaymentSDto of(String orderId, Long amount, String paymentKey) {
        return new ConfirmPaymentSDto(
                orderId,
                amount,
                paymentKey
        );
    }
}
