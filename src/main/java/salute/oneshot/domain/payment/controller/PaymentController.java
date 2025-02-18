package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.PaymentStatus;
import salute.oneshot.domain.payment.service.PaymentService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping("/payments/{reservationId}/confirm")
    @PostMapping("/payments/confirm")
    public ResponseEntity<ApiResponse<ConfirmPaymentResponseDto>> confirmPayment(
//            @PathVariable Long reservationId,
            @RequestBody ConfirmPaymentRequestDto requestDto
    ) {
//        ConfirmPaymentSDto sdto = ConfirmPaymentSDto.of(reservationId, requestDto.getOrderId(), requestDto.getAmount(), requestDto.getPaymentKey());
        ConfirmPaymentSDto sdto = ConfirmPaymentSDto.of(requestDto.getOrderId(), requestDto.getAmount(), requestDto.getPaymentKey());
        ConfirmPaymentResponseDto responseDto = paymentService.confirmPayment(sdto);

        return ResponseEntity.ok()
                .body(ApiResponse.success(ApiResponseConst.ADD_PMNT_SUCCESS, responseDto));
    }
}