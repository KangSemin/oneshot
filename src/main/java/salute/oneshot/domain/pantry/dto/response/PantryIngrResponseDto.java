package salute.oneshot.domain.pantry.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.pantry.entity.PantryIngredient;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PantryIngrResponseDto {

    private final Long id;
    private final IngrResponseDto ingredient;

    public static PantryIngrResponseDto from(PantryIngredient pantryIngredient) {
        return new PantryIngrResponseDto(pantryIngredient.getId(), IngrResponseDto.from(pantryIngredient.getIngredient()));
    }
}
