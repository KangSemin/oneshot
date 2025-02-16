package salute.oneshot.domain.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmPaymentRequestDto {

    private final String password;
}
