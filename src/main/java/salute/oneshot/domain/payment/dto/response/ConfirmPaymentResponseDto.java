package salute.oneshot.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.payment.entity.Payment;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentResponseDto {

    private final String orderId;
    private final Long amount;
    private final String paymentKey;
}
