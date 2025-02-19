package salute.oneshot.domain.order.repository;

import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.order.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByUser_Id(Long userId, Pageable pageable);

    @Query("SELECT o.orderNumber FROM Order o WHERE o.orderNumber LIKE CONCAT(:orderDate, '%') ORDER BY o.orderNumber DESC")
    List<String> findLastOrderNumberByDate(@Param("orderDate") String orderDate, Pageable pageable);

}
