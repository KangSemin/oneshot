package salute.oneshot.domain.cocktail.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientRequestDto {

    private final Long ingredientId;
    private final String volume;

    public static IngredientRequestDto of(Long ingredientId, String volume) {
        return new IngredientRequestDto(ingredientId, volume);
    }
}
