package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.entity.PaymentStatus;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
//    private final PaymentClient paymentClient;

    @Transactional
    public PaymentResponseDto createPayment(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));
        isOrderStatusPendingPayment(foundOrder);

        // 다른 대기 중인 경제가 있을 경우 처리

        Payment newPayment = Payment.from(foundOrder);
        paymentRepository.save(newPayment);

        return PaymentResponseDto.from(newPayment);
    }

    // TODO: 트랜잭션을 조회에 쓰는 이유가 같은 시점의 정보임을 보장하기 위해서라면 -> 이 경우는 값이 바뀔 가능성이 없어서 사용할 필요 X?
    @Transactional(readOnly = true)
    public PaymentResponseDto findPayment(Long userId, Long paymentId) {
        Payment foundPayment = getPayment(paymentId);

        // TODO: 쓸 데 없는 엔티티 조회가 많이 이루어진다 vs 중복된 값을 가진 필드를 Order나 Payment에 추가한다 -> 필요한 값만 가져오는 쿼리를 생성?
        isPaymentOwnedByUser(foundPayment, userId);

        return PaymentResponseDto.from(foundPayment);
    }

    @Transactional
    public PaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {
        Payment foundPayment = getPayment(sdto.getPaymentId());

        isPaymentOwnedByUser(foundPayment, sdto.getUserId());

        if (!foundPayment.getStatus().equals(PaymentStatus.PENDING)) {
            throw new ConflictException(ErrorCode.PAYMENT_ALREADY_CONFIRMED);
        }

        isOrderStatusPendingPayment(foundPayment.getOrder());

        // 비밀번호가 확인되면 결제 승인
        boolean matches = passwordEncoder.matches(sdto.getPassword(), foundPayment.getOrder().getUser().getPassword());

        if (matches) {
            foundPayment.getOrder().updateStatus(OrderStatus.PROCESSING);
            foundPayment.updateStatus(PaymentStatus.APPROVED);
        } else {
            foundPayment.updateStatus(PaymentStatus.DECLINED);
        }

        return PaymentResponseDto.from(foundPayment);
    }

    private Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
    }

    private void isPaymentOwnedByUser(Payment payment, Long userId) {
        if (!payment.getOrder().getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorCode.PAYMENT_UNAUTHORIZED);
        }
    }

    private void isOrderStatusPendingPayment(Order order) {
        if (!order.getStatus().equals(OrderStatus.PENDING_PAYMENT)) {
            throw new ConflictException(ErrorCode.ORDER_ALREADY_PAID);
        }
    }

//    public ConfirmPaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {
//        final ConfirmPaymentResponseDto responseDto = paymentClient.confirmPayment(sdto);
////        final Payment payment = responseDto.toPayment(sdto.getOrderId());
//
////        paymentRepository.save(payment);
//        return responseDto;
//    }
}
