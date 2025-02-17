package salute.oneshot.domain.ingredient.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngrResponseDto implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final IngredientCategory category;
    private final Double AVB;


    public static IngrResponseDto from(Ingredient ingredient) {
        return new IngrResponseDto(
                ingredient.getId(), ingredient.getName(), ingredient.getDescription(),
                ingredient.getCategory(), ingredient.getAvb()
        );
    }
}

