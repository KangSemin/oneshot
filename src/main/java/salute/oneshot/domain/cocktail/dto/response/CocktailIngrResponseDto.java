package salute.oneshot.domain.cocktail.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailIngrResponseDto implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final IngredientCategory category;
    private final Double AVB;


    public static CocktailIngrResponseDto from(Ingredient ingredient){
        return new CocktailIngrResponseDto(
                ingredient.getId(), ingredient.getName(), ingredient.getDescription(),
                ingredient.getCategory(), ingredient.getAvb());
    }
}

