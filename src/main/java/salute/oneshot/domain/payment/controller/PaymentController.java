package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.PaymentStatus;
import salute.oneshot.domain.payment.service.PaymentService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/orders/{orderId}/payments")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> createPayment(
            @PathVariable Long orderId
    ) {
        PaymentResponseDto responseDto = paymentService.createPayment(orderId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseConst.ADD_PAYMENT_SUCCESS, responseDto));
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> findPayment(
            @PathVariable Long paymentId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        PaymentResponseDto responseDto = paymentService.findPayment(userDetails.getId(), paymentId);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.GET_PAYMENT_SUCCESS, responseDto));
    }

    @PostMapping("/payments/{paymentId}/confirm")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> confirmPayment(
            @PathVariable Long paymentId,
            @RequestBody ConfirmPaymentRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ConfirmPaymentSDto sdto = ConfirmPaymentSDto.of(userDetails.getId(), paymentId, requestDto.getPassword());
        PaymentResponseDto responseDto = paymentService.confirmPayment(sdto);

        boolean isApproved = responseDto.getStatus().equals(PaymentStatus.APPROVED);

        return ResponseEntity.ok()
                .body(ApiResponse.success(
                        isApproved ? ApiResponseConst.PAYMENT_APPROVED : ApiResponseConst.PAYMENT_DECLINED,
                        responseDto
                ));
    }
}