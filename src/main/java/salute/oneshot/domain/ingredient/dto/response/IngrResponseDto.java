package salute.oneshot.domain.ingredient.dto.response;

import org.hibernate.validator.constraints.Range;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

public class IngrResponseDto {

    private final Long id;
    private final String name;
    private final String description;
    private final IngredientCategory category;

    @Range(min = 0, max = 100L)
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
