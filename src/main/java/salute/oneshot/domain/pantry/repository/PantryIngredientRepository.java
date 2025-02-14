package salute.oneshot.domain.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.pantry.entity.PantryIngredient;

public interface PantryIngredientRepository extends JpaRepository<PantryIngredient,Long> {

}
