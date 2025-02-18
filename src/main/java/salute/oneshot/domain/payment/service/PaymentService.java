package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.feign.TossConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.feign.TossPaymentResponseDto;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.domain.payment.util.PaymentClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
    private final PaymentClient paymentClient;

    @Transactional
    public ConfirmPaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {

        log.info("orderId: " + sdto.getOrderId());
        log.info("paymentKey: " + sdto.getPaymentKey());
        log.info("amount: " + sdto.getAmount());

        TossConfirmPaymentRequestDto tossRequestDto = TossConfirmPaymentRequestDto.of(sdto.getOrderId(), sdto.getAmount(), sdto.getPaymentKey());
        TossPaymentResponseDto tossResponseDto = paymentClient.confirmPayment(tossRequestDto);

        Payment payment = Payment.fromDto(tossResponseDto);
        paymentRepository.save(payment);

        return ConfirmPaymentResponseDto.from(tossResponseDto);
    }

//    public GetPaymentResponseDto GetPayment(String reason) {
//        Payment foundPayment = paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
//
//        return paymentResponseDto.from(foundPayment);
//    }
//
//    @Transactional
//    public GetDetailedPaymentResponseDto GetDetailedPayment(Long orderId) {
//        TossPaymentResponseDto tossResponseDto = paymentClient.getPaymentByOrderId(orderId);
//
//        return PaymentResponseDto.from(tossResponseDto);
//    }
//
//    @Transactional
//    public CancelPaymentResponseDto cancelPayment(String reason) {
//        TossCancelPaymentRequestDto tossRequestDto = TossCancelPaymentRequestDto.from(reason);
//        TossPaymentResponseDto tossResponseDto = paymentClient.cancelPayment(tossRequestDto);
//
//        Payment payment = Payment.fromDto(tossResponseDto);
//        paymentRepository.save(payment);
//
//        return CancelPaymentResponseDto.from(tossResponseDto);
//    }
}
