package salute.oneshot.domain.cocktail.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCocktailRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    private final String description;
    @NotBlank
    private final String recipe;
    @NotNull
    private final List<IngredientRequestDto> ingredientList;


}
