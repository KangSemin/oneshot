package salute.oneshot.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
