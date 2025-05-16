package salute.oneshot.domain.cocktail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;

public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient,Long> {

}
