package salute.oneshot.domain.cocktail.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto.IngredientRequestdto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCocktailSDto {

    Long userId;
    String name;
    String recipe;
    String description;
    List<IngredientRequestdto> ingredientList;


    public static CreateCocktailSDto of(Long userId, String name, String recipe, String description,
        List<IngredientRequestdto> ingredientList) {
        return new CreateCocktailSDto(userId, name, recipe, description, ingredientList);
    }

}
