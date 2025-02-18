package salute.oneshot.domain.payment.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.payment.dto.feign.TossPaymentResponseDto;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPaymentResponseDto {

    private final String paymentKey;
    private final String status;
    private final String orderId;
    private final String orderName;
    private final Long totalAmount;

    public static ConfirmPaymentResponseDto from(TossPaymentResponseDto dto) {
        return new ConfirmPaymentResponseDto(
                dto.getPaymentKey(),
                dto.getStatus(),
                dto.getOrderId(),
                dto.getOrderName(),
                dto.getTotalAmount()
        );
    }
}
