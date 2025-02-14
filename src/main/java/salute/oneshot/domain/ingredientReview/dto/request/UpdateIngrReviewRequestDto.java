package salute.oneshot.domain.ingredientReview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class UpdateIngrReviewRequestDto {

    @NotNull
    @Range(min = 0, max = 5)
    private Byte star;

    @NotBlank
    private String content;

}