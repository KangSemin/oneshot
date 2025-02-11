package salute.oneshot.domain.ingredient.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
public class UpdateIngrRequestDto {

    private String name;
    private String description;
    private IngredientCategory category;

    @NotNull
    @Range(min = 0, max = 100)
    private Double avb;

}
