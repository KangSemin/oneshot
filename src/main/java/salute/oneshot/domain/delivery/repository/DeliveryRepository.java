package salute.oneshot.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.delivery.entity.Delivery;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM Delivery d WHERE d.order.id = :orderId)")
    boolean existsByOrderId(@Param("orderId") Long orderId);

    Optional<Delivery> findByOrderId(Long orderId);
}
