package salute.oneshot.domain.cocktail.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCocktailRequestDto {

    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @NotNull
    private final String recipe;
    @NotNull
    private final List<IngredientRequestDto> ingredientList;


}
