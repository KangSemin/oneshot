package salute.oneshot.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.payment.entity.Payment;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentResponseDto {
    private final Long paymentId;
    private final Long orderId;
    private final Long amount;

    public static PaymentResponseDto from(Payment payment) {
        return new PaymentResponseDto(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getAmount()
        );
    }
}