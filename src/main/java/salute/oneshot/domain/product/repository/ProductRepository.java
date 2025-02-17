package salute.oneshot.domain.product.repository;

import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            "SELECT p FROM Product p " +
                    "WHERE (p.category = :category OR :category IS NULL)" +
                    "AND (p.status <> :status)"
    )
    Page<Product> findByCategoryANDStatusNot(@Param("status") ProductStatus status,
                                             @Param("category") ProductCategory category, Pageable pageable);

    @Query(
            "SELECT p FROM Product p " +
                    "WHERE (p.id = :productId AND p.status <> :status)"
    )
    Optional<Product> findByIdANDStatusNot(@Param("status") ProductStatus status,
                                           @Param("productId")Long productId);
}