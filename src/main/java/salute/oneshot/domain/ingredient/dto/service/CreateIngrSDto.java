package salute.oneshot.domain.ingredient.dto.service;

import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
public class CreateIngrSDto {

    private final String name;
    private final String description;
    private final IngredientCategory category;

    private final Double avb;


    private CreateIngrSDto(String name, String description, IngredientCategory category, Double avb) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.avb = avb;
    }

    public static CreateIngrSDto of(String name, String description, IngredientCategory category, Double avb) {
        return new CreateIngrSDto(name, description, category, avb);
    }
}
