package salute.oneshot.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.payment.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findPaymentByPaymentKey(String paymentKey);
}
