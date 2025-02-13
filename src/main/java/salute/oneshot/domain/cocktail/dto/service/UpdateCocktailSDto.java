package salute.oneshot.domain.cocktail.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCocktailSDto {

    private final Long cocktailId;
    private final Long userId;
    private final String name;
    private final String recipe;
    private final String description;
    private final List<IngredientRequestDto> ingredientList;


    public static UpdateCocktailSDto of(Long cocktailId, Long userId, String name, String recipe, String description,
        List<IngredientRequestDto> ingredientList) {
        return new UpdateCocktailSDto(
            cocktailId, userId, name, recipe, description, ingredientList);
    }

}
