package salute.oneshot.domain.ingredient.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
public class CreateIngrRequestDto {

    @NotNull(message = "재료명은 필수입니다.")
    private String name;
    @NotNull(message = "설명은 필수입니다.")
    private String descriptions;
    @NotNull(message = "카테고리는 필수입니다.")
    private IngredientCategory category;

    @Range(min = 0, max = 100)
    private Double avb;

}
