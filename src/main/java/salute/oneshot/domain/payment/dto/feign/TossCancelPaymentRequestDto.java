package salute.oneshot.domain.payment.dto.feign;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossCancelPaymentRequestDto {

    private final String cancelReason;

    public static TossCancelPaymentRequestDto from(String cancelReason) {
        return new TossCancelPaymentRequestDto(
                cancelReason
        );
    }
}
