package salute.oneshot.domain.ingredient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.ingredient.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


}
