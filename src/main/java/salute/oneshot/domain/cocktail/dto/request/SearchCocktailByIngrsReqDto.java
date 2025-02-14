package salute.oneshot.domain.cocktail.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCocktailByIngrsReqDto {
    @NotNull
    private final List<Long> ingredientIds;
}
