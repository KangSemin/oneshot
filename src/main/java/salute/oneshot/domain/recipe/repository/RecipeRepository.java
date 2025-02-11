package salute.oneshot.domain.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.recipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
