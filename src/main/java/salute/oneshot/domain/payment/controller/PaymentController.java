package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final OrderPaymentFacade orderPaymentFacade;

    @PostMapping("/payments/confirm")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> confirmPayment(
            @RequestBody ConfirmPaymentRequestDto requestDto
    ) {
        ConfirmPaymentSDto sdto = ConfirmPaymentSDto.of(requestDto.getOrderId(), requestDto.getAmount(), requestDto.getPaymentKey());
        PaymentResponseDto responseDto = orderPaymentFacade.confirmPayment(sdto);

        return ResponseEntity.ok()
                .body(ApiResponse.success(ApiResponseConst.ADD_PMNT_SUCCESS, responseDto));
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> findPayment(
            @PathVariable Long paymentId
    ) {
        PaymentResponseDto responseDto = orderPaymentFacade.getPayment(paymentId);

        return ResponseEntity.ok()
                .body(ApiResponse.success(ApiResponseConst.ADD_PMNT_SUCCESS, responseDto));
    }
}