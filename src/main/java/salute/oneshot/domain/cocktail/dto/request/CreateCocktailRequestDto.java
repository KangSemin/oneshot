package salute.oneshot.domain.cocktail.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCocktailRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    private final String description;
    @NotBlank
    private final String recipe;

    List<IngredientRequestdto> ingredientList;

    @Getter
    @AllArgsConstructor
    public static class IngredientRequestdto {

        private final Long ingredientId;
        private final String volume;
    }
}
