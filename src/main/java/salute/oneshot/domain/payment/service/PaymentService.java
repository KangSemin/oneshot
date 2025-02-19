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
        log.info("amount: " + sdto.getAmount());
        log.info("paymentKey: " + sdto.getPaymentKey());

        TossConfirmPaymentRequestDto tossRequestDto = TossConfirmPaymentRequestDto.of(sdto.getOrderId(), sdto.getAmount(), sdto.getPaymentKey());

//        logStatus(sdto);
        TossPaymentResponseDto tossResponseDto = paymentClient.confirmPayment(tossRequestDto);
//        logStatus(sdto);

        Payment payment = Payment.fromDto(tossResponseDto);
        paymentRepository.save(payment);

////        String orderNo;
////        NOT FOUND 처리해야 하나? 트랜잭션 때문에 결제 저장 안되면 망하는데
//        Order order = orderRepository.findByOrderNo().orElseThrow();
////        이것도 뭔가 필요없는 검증같기도 하고
//        if (!order.isValidStatusChange(OrderStatus.PROCESSING)) {
//            throw new ConflictException(ErrorCode.ORDER_STATUS_NOT_POSSIBL);
//        }
//        order.updateStatus(OrderStatus.PROCESSING);

        return ConfirmPaymentResponseDto.from(tossResponseDto);
    }

    private void logStatus(ConfirmPaymentSDto sdto) {
        TossPaymentResponseDto tossResponseDto = paymentClient.getPaymentByOrderId(sdto.getOrderId());
        log.info(tossResponseDto.getStatus());
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
