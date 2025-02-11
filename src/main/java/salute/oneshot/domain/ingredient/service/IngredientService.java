package salute.oneshot.domain.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngrResponseDto createIngredient(CreateIngrSDto request) {

        Ingredient ingredient = ingredientRepository.save(Ingredient.of(
            request.getName(), request.getAvb(), request.getDescription(), request.getCategory()));

        return IngrResponseDto.from(ingredient);
    }
}
