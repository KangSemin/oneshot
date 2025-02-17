package salute.oneshot.domain.ingredient.dto.service;

import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
public class UpdateIngrSDto {

    private final Long id;
    private final String name;
    private final String description;
    private final IngredientCategory category;

    private final Double avb;


    private UpdateIngrSDto(Long id, String name, String description, IngredientCategory category,
        Double avb) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.avb = avb;
    }

    public static UpdateIngrSDto of(Long id, String name, String description,
        IngredientCategory category, Double avb) {
        return new UpdateIngrSDto(id, name, description, category, avb);
    }
}
