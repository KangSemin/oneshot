package salute.oneshot.domain.recipeReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.RecipeType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailResponseDto {

    private final Long id;
    private final String name;
    private final String description;
    private final String recipe;
    private final RecipeType type;
    private final Integer likeCounts;
    private final Long userid;

    public static CocktailResponseDto from(Cocktail cocktail) {
        return new CocktailResponseDto(cocktail.getId(), cocktail.getName(), cocktail.getDescription(), cocktail.getRecipe(), cocktail.getType(),
                cocktail.getLikeCounts(), cocktail.getId());
    }
}
