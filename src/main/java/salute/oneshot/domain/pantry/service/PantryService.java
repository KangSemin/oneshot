package salute.oneshot.domain.pantry.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.pantry.dto.response.PantryResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddIngrToPantrySDto;
import salute.oneshot.domain.pantry.dto.service.RemoveIngrFromPantrySDto;
import salute.oneshot.domain.pantry.entity.Pantry;
import salute.oneshot.domain.pantry.repository.PantryRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public PantryResponseDto addIngredientToPantry(AddIngrToPantrySDto request) {

        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        boolean exists = pantryRepository.existsByUserIdAndIngredientId(request.getUserId(),request.getIngredientId());

        if (exists) {
            throw new ConflictException(ErrorCode.DUPLICATE_INGREDIENT);
        }
        Pantry pantry = Pantry.of(request.getUserId(), ingredient);
        pantryRepository.save(pantry);
        return PantryResponseDto.from(pantry);
    }

    public List<PantryResponseDto> getPantry(Long userId) {

        List<Pantry> pantry = pantryRepository.findAllByUserId(userId);
        return pantry.stream().map(PantryResponseDto::from).toList();
    }

    @Transactional
    public void removeIngredientsFromPantry(RemoveIngrFromPantrySDto sDto) {

        pantryRepository.deleteByUserIdAndIngredientIds(sDto.getUserId(),
            sDto.getIngredientIds());
    }


    @Transactional
    public void clearPantryIngredients(Long userId) {
        pantryRepository.deleteByUserId(userId);
    }

}
