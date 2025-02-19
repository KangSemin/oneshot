package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.feign.TossConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.entity.TossPayment;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.global.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Transactional
    public PaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {

//        log.info("orderId: " + sdto.getOrderId());
//        log.info("amount: " + sdto.getAmount());
//        log.info("paymentKey: " + sdto.getPaymentKey());

        TossConfirmPaymentRequestDto tossRequestDto = TossConfirmPaymentRequestDto.of(sdto.getOrderId(), sdto.getAmount(), sdto.getPaymentKey());

        TossPayment tossPayment = paymentClient.confirmPayment(tossRequestDto);

        Payment payment = Payment.fromDto(tossPayment);
        paymentRepository.save(payment);

////        NOT FOUND 처리해야 하나? 트랜잭션 때문에 결제 저장 안되면 망하는데
//        Order order = orderRepository.findByOrderNoAndUserId(orderNo, sdto.getUserId()).orElseThrow();
////        이것도 뭔가 필요없는 검증같기도 하고
//        if (!order.isValidStatusChange(order.getStatus(), OrderStatus.PROCESSING)) {
//            throw new ConflictException(ErrorCode.INVALID_ORDER_STATUS);
//        }
//        order.updateStatus(OrderStatus.PROCESSING);

        return PaymentResponseDto.from(tossPayment);
    }

    private void logStatus(ConfirmPaymentSDto sdto) {
        TossPayment tossResponseDto = paymentClient.getPaymentByOrderId(sdto.getOrderId());
        log.info(tossResponseDto.getStatus().toString());
    }

    public PaymentResponseDto getPayment(Long paymentId) {
        Payment foundPayment = paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
        return PaymentResponseDto.from(foundPayment);
    }

//    @Transactional
//    public GetDetailedPaymentResponseDto GetPaymentDetails(Long orderId) {
//        TossPaymentResponseDto tossResponseDto = paymentClient.getPaymentByOrderId(orderId);
//
//        return PaymentResponseDto.from(tossResponseDto);
//    }

//    @Transactional
//    public PaymentResponseDto cancelPayment(String reason) {
//        TossCancelPaymentRequestDto tossRequestDto = TossCancelPaymentRequestDto.from(reason);
//        TossPayment tossPayment = paymentClient.cancelPayment(tossRequestDto);
//
//        Payment payment = paymentRepository.findPaymentByPaymentKey(tossPayment.getPaymentKey()).orElseThorw(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
//        payment.updateStatus(tossPayment.getStatus());
//        paymentRepository.save(payment);
//
//        return PaymentResponseDto.from(tossPayment);
//    }
}
