package salute.oneshot.domain.pantry.service;


import java.util.Optional;
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
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
    private final UserRepository userRepository;
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

        Pantry pantry = pantryRepository.findByUser_Id((userId));

        return PantryResponseDto.from(pantry);
    }

    @Transactional
    public void removeIngredientFromPantry(RemoveIngrFromPantrySDto sDto) {

        Pantry pantry = findPantry(sDto.getUserId());

        Optional<PantryIngredient> targetOpt = pantry.getPantryIngredientList().stream()
            .filter(pi -> pi.getIngredient().getId().equals(sDto.getIngredientId()))
            .findFirst();

        if (targetOpt.isEmpty()) {
            throw new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND);
        }

        PantryIngredient target = targetOpt.get();
        pantry.getPantryIngredientList().remove(target);
    }

    @Transactional
    public void clearPantryIngredients(Long userId) {

        Pantry pantry = findPantry(userId);

        if (!pantry.getPantryIngredientList().isEmpty()) {
            pantryIngredientRepository.deleteByPantryId(pantry.getId());
        }
    }

    private Pantry findPantry(Long userId) {
        Pantry pantry = pantryRepository.findByUser_Id((userId));

        if (pantry == null) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
            pantry = Pantry.of(user);
            pantryRepository.save(pantry);
        }

        return pantry;
    }

}
