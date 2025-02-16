package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.global.exception.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Transactional
    public PaymentResponseDto createPayment(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));
        Payment newPayment = Payment.from(foundOrder);
        paymentRepository.save(newPayment);
        return PaymentResponseDto.from(newPayment);
    }

    public ConfirmPaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {
        final ConfirmPaymentResponseDto responseDto = paymentClient.confirmPayment(sdto);
//        final Payment payment = responseDto.toPayment(sdto.getOrderId());

//        paymentRepository.save(payment);
        return responseDto;
    }
}
