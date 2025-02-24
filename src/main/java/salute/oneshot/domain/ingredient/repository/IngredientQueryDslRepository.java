package salute.oneshot.domain.ingredient.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;

import java.util.List;

public interface IngredientQueryDslRepository {

    List<IngrResponseDto> findIngrByCondition(List<Long> ingrIdList, Pageable pageable);
}
