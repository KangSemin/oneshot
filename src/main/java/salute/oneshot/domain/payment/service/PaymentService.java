package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.feign.TossCancelPaymentRequestDto;
import salute.oneshot.domain.payment.dto.feign.TossConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.entity.TossPayment;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {

        logStatus(sdto);

        // 클라이언트가 금액을 변경해서 결제를 생성했을 가능성이 있음
        // 결제 API 뒤에 검증하면 DB의 부담이 줄어들 수 있지만 실패시 결제를 취소해야 하는 불편함이 생김
        Order order = orderRepository.findByOrderNumber(sdto.getOrderNumber()).orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));
        if (!order.getAmount().equals(sdto.getAmount())) {
            throw new ConflictException(ErrorCode.WRONG_PAYMENT_AMOUNT);
        }

        // 토스 페이먼츠 결제 승인 요청 API
        TossConfirmPaymentRequestDto tossRequestDto = TossConfirmPaymentRequestDto.of(
                sdto.getOrderNumber(),
                sdto.getAmount(),
                sdto.getPaymentKey()
        );
        TossPayment tossPayment = paymentClient.confirmPayment(tossRequestDto);


        Payment payment = Payment.fromDto(tossPayment);
        paymentRepository.save(payment);

        return PaymentResponseDto.from(tossPayment);
    }

    public PaymentResponseDto getPayment(String paymentKey) {
        Payment foundPayment = paymentRepository.findPaymentByPaymentKey(paymentKey).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
        return PaymentResponseDto.from(foundPayment);
    }

    private void logStatus(ConfirmPaymentSDto sdto) {
        TossPayment tossResponseDto = paymentClient.getPaymentByOrderId(sdto.getOrderNumber());
        log.info(
                "orderId: " + sdto.getOrderNumber() +
                ", amount: " + sdto.getAmount() +
                ", paymentKey: " + sdto.getPaymentKey() +
                ", status: " + tossResponseDto.getStatus().toString()
        );
    }

    @Transactional
    public TossPayment GetPaymentDetails(String orderNumber) {
        return paymentClient.getPaymentByOrderId(orderNumber);
    }

    @Transactional
    public PaymentResponseDto cancelPayment(String reason) {
        TossCancelPaymentRequestDto tossRequestDto = TossCancelPaymentRequestDto.from(reason);
        TossPayment tossPayment = paymentClient.cancelPayment(tossRequestDto);

        Payment payment = paymentRepository.findPaymentByPaymentKey(tossPayment.getPaymentKey()).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
        payment.updateStatus(tossPayment.getStatus());
        paymentRepository.save(payment);

        return PaymentResponseDto.from(tossPayment);
    }
}
