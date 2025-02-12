package salute.oneshot.domain.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.error.ErrorResponse;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngrResponseDto createIngredient(CreateIngrSDto request) {

        Ingredient ingredient = ingredientRepository.save(Ingredient.of(
            request.getName(), request.getDescription(), request.getCategory(), request.getAvb()));

        return IngrResponseDto.from(ingredient);
    }

    public IngrResponseDto updateIngredient(UpdateIngrSDto request) {

        Ingredient ingredient = ingredientRepository.findById(request.getId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        ingredient.update(request.getName(), request.getDescription(), request.getCategory(),request.getAvb());
        return IngrResponseDto.from(ingredient);
    }

    public void deleteIngredient(Long ingredientId) {

        ingredientRepository.deleteById(ingredientId);
    }
}
