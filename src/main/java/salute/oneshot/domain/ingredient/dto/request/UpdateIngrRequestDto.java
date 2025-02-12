package salute.oneshot.domain.ingredient.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
@AllArgsConstructor
public class UpdateIngrRequestDto {

    private String name;
    private String description;
    private IngredientCategory category;

    @NotNull
    @Range(min = 0, max = 100)
    private Double avb;

}
