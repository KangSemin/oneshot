package salute.oneshot.domain.ingredient.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public IngrResponseDto getIngredient(Long ingredientId) {

        Ingredient ingredient = findById(ingredientId);

        return IngrResponseDto.from(ingredient);
    }

    public Page<IngrResponseDto> getAllIngredients(Pageable pageable) {
        
        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);

        return ingredients.map(IngrResponseDto::from);
    }

    @Transactional
    public IngrResponseDto createIngredient(CreateIngrSDto request) {

        Ingredient ingredient = ingredientRepository.save(Ingredient.of(
            request.getName(), request.getDescription(), request.getCategory(), request.getAvb()));

        return IngrResponseDto.from(ingredient);
    }

    @Transactional
    public IngrResponseDto updateIngredient(UpdateIngrSDto request) {

        Ingredient ingredient = findById(request.getId());

        ingredient.update(request.getName(), request.getDescription(), request.getCategory(),
            request.getAvb());
        return IngrResponseDto.from(ingredient);
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) {

        ingredientRepository.deleteById(ingredientId);
    }

    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));
    }


}
