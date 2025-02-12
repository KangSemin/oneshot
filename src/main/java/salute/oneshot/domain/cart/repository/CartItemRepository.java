package salute.oneshot.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}