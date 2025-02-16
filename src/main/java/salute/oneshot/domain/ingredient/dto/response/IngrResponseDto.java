package salute.oneshot.domain.ingredient.dto.response;

import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

import java.io.Serializable;

@Getter
public class IngrResponseDto implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final IngredientCategory category;
    private final Double AVB;

    private IngrResponseDto(Long id, String name, String description, IngredientCategory category,
        Double AVB) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.AVB = AVB;
    }

    public static IngrResponseDto from(Ingredient ingredient) {
        return new IngrResponseDto(
            ingredient.getId(), ingredient.getName(), ingredient.getDescription(),
            ingredient.getCategory(), ingredient.getAvb()
        );
    }
}
