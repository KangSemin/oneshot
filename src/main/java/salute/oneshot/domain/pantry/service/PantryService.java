package salute.oneshot.domain.pantry.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.pantry.dto.response.PantryIngrResponseDto;
import salute.oneshot.domain.pantry.dto.response.PantryResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddIngrToPantrySDto;
import salute.oneshot.domain.pantry.dto.service.RemoveIngrFromPantrySDto;
import salute.oneshot.domain.pantry.entity.Pantry;
import salute.oneshot.domain.pantry.entity.PantryIngredient;
import salute.oneshot.domain.pantry.repository.PantryIngredientRepository;
import salute.oneshot.domain.pantry.repository.PantryRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
    private final IngredientRepository ingredientRepository;
    private final PantryIngredientRepository pantryIngredientRepository;

    @Transactional
    public PantryIngrResponseDto addIngredientToPantry(AddIngrToPantrySDto request) {

        Pantry pantry = findPantry(request.getUserId());

        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        boolean exists = pantry.getPantryIngredientList().stream()
            .anyMatch(pi -> pi.getIngredient().getId().equals(ingredient.getId()));
        if (exists) {
            throw new ConflictException(ErrorCode.DUPLICATE_INGREDIENT);
        }

        PantryIngredient pantryIngredient = PantryIngredient.of(pantry, ingredient);
        pantryIngredientRepository.save(pantryIngredient);
        pantry.getPantryIngredientList().add(pantryIngredient);

        return PantryIngrResponseDto.from(pantryIngredient);
    }

    @Transactional(readOnly = true)
    public PantryResponseDto getPantry(Long userId) {

        Pantry pantry = pantryRepository.findByUserId((userId));
        return PantryResponseDto.from(pantry);
    }

    @Transactional
    public void removeIngredientFromPantry(RemoveIngrFromPantrySDto sDto) {

        pantryIngredientRepository.deleteByPantryIdAndIngredientIds(sDto.getUserId(),
            sDto.getIngredientIds());
    }


    @Transactional
    public void clearPantryIngredients(Long userId) {

        Long pantryId = pantryRepository.findIdByUserId(userId);

        if (pantryId != null) {
            pantryIngredientRepository.deleteByPantryId(pantryId);
        }
    }

    private Pantry findPantry(Long userId) {
        Pantry pantry = pantryRepository.findByUserId((userId));

        if (pantry == null) {
            pantry = Pantry.of(userId);
            pantryRepository.save(pantry);
        }
        return pantry;
    }

}
