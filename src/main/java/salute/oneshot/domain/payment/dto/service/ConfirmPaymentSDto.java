package salute.oneshot.domain.payment.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentSDto {

    private final String orderNumber;
    private final Long amount;
    private final String paymentKey;

    public static ConfirmPaymentSDto of(String orderNumber, Long amount, String paymentKey) {
        return new ConfirmPaymentSDto(
                orderNumber,
                amount,
                paymentKey
        );
    }
}
