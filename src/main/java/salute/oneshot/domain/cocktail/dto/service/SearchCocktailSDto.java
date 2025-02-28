package salute.oneshot.domain.cocktail.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.RecipeType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchCocktailSDto {

    private final List<Long> ingredientIds;
    private final RecipeType recipeType;
    private final int page;
    private final int size;

    public static SearchCocktailSDto of(List<Long> ingredientIds, RecipeType recipeType, int page, int size) {
        return new SearchCocktailSDto(ingredientIds, recipeType, page, size);
    }

}
