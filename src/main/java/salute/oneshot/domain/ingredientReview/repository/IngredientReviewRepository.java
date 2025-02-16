package salute.oneshot.domain.ingredientReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

public interface IngredientReviewRepository extends JpaRepository<IngredientReview, Long> {

}
