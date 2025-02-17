package salute.oneshot.domain.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.shipping.entity.Shipping;

import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM Shipping s WHERE s.order.id = :orderId)")
    boolean existsByOrderId(@Param("orderId") Long orderId);

    Optional<Shipping> findByOrderId(Long orderId);
}
