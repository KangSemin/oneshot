package salute.oneshot.domain.payment.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentSDto {

    private final Long userId;
    private final Long paymentId;
    private final String password;

    public static ConfirmPaymentSDto of(Long userId, Long paymentId, String password) {
        return new ConfirmPaymentSDto(
                userId,
                paymentId,
                password
        );
    }
}
