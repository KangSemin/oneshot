package salute.oneshot.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.entity.Payment;
import salute.oneshot.domain.payment.entity.PaymentStatus;
import salute.oneshot.domain.payment.repository.PaymentRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;

import java.util.Optional;

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
        Payment newPayment = Payment.from(foundOrder);
        paymentRepository.save(newPayment);
        return PaymentResponseDto.from(newPayment);
    }

    // TODO: 트랜잭션을 조회에 쓰는 이유가 같은 시점의 정보임을 보장하기 위해서라면 -> 이 경우는 값이 바뀔 가능성이 없어서 사용할 필요 X?
    @Transactional(readOnly = true)
    public PaymentResponseDto findPayment(Long userId, Long paymentId) {
        Payment foundPayment = getPayment(paymentId);

        // TODO: 쓸 데 없는 엔티티 조회가 많이 이루어진다 vs 중복된 값을 가진 필드를 Order나 Payment에 추가한다 -> 필요한 값만 가져오는 쿼리를 생성?
        if (!foundPayment.getOrder().getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorCode.PAYMENT_UNAUTHORIZED);
        }

        return PaymentResponseDto.from(foundPayment);
    }

    @Transactional
    public PaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {
        Payment foundPayment = getPayment(sdto.getPaymentId());

        User foundUser = foundPayment.getOrder().getUser();
        if (!foundUser.getId().equals(sdto.getUserId())) {
            throw new UnauthorizedException(ErrorCode.PAYMENT_UNAUTHORIZED);
        }

        boolean matches = passwordEncoder.matches(sdto.getPassword(), foundUser.getPassword());
        foundPayment.updateStatus(
                matches ? PaymentStatus.APPROVED : PaymentStatus.DECLINED
        );

        return PaymentResponseDto.from(foundPayment);
    }

    private Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
    }

//    public ConfirmPaymentResponseDto confirmPayment(ConfirmPaymentSDto sdto) {
//        final ConfirmPaymentResponseDto responseDto = paymentClient.confirmPayment(sdto);
////        final Payment payment = responseDto.toPayment(sdto.getOrderId());
//
////        paymentRepository.save(payment);
//        return responseDto;
//    }
}
