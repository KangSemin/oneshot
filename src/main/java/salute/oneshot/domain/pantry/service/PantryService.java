package salute.oneshot.domain.pantry.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.pantry.dto.response.PantryIngrResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddPantryIngredientSDto;
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
    public PantryIngrResponseDto addIngredientToPantry(AddPantryIngredientSDto request) {

        Long userId = request.getUserId();

        Pantry pantry = pantryRepository.findByUser_Id((userId));

        if (pantry == null) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
            pantry = Pantry.of(user);
            pantryRepository.save(pantry);
        }

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

}
