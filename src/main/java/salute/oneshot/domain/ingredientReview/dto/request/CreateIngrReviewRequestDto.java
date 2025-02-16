package salute.oneshot.domain.ingredientReview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class CreateIngrReviewRequestDto {

    @NotNull
    @Range(min = 0, max = 5)
    private Byte star;

    @NotBlank(message = "설명은 필수입니다.")
    private String content;
}
