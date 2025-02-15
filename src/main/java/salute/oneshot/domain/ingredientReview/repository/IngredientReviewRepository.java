package salute.oneshot.domain.ingredientReview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

public interface IngredientReviewRepository extends JpaRepository<IngredientReview, Long> {

    Page<IngredientReview> findAllByUser_Id(Long userId, Pageable pageable);
    
    Page<IngredientReview> findAllByIngredient_Id(Long ingredientId, Pageable pageable);
}
