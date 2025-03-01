package salute.oneshot.domain.cocktail.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.ingredient.entity.Ingredient;

public interface CocktailQueryDslRepository{
    Page<CocktailResponseDto> searchCocktailsByIngredients(List<Ingredient> selectedIngrs, Pageable pageable);

}
