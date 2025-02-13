package salute.oneshot.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.cart.entity.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserIdAndIsOrderedFalse(Long userId);
}