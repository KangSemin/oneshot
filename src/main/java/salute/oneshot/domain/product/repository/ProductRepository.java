package salute.oneshot.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
