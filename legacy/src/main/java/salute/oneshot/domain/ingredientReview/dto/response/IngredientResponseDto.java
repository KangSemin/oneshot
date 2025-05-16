package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientResponseDto {

    private final Long ingredientId;
    private final String name;
    private final Double avb;
    private final String description;
    private final IngredientCategory category;

    public static IngredientResponseDto from(Ingredient ingredient) {
        return new IngredientResponseDto(ingredient.getId(), ingredient.getName(),
                ingredient.getAvb(), ingredient.getDescription(), ingredient.getCategory());
    }
}