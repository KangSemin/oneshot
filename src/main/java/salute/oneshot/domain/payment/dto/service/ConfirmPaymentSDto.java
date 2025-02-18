package salute.oneshot.domain.payment.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentSDto {

//    private final Long reservationId;
    private final String orderId;
    private final String amount;
    private final String paymentKey;

//    public static ConfirmPaymentSDto of(Long reservationId, String orderId, int amount, String paymentKey) {
    public static ConfirmPaymentSDto of(String orderId, String amount, String paymentKey) {
        return new ConfirmPaymentSDto(
//                reservationId,
                orderId,
                amount,
                paymentKey
        );
    }
}
