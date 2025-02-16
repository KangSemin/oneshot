package salute.oneshot.domain.recipeReview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;

public interface RecipeReviewRepository extends JpaRepository<RecipeReview, Long> {

    Page<RecipeReview> findAllByCocktail_Id(Long cocktailId, Pageable pageable);
}
