package salute.oneshot.domain.recipeReview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class CreateRecipeReviewRequestDto {
    @NotBlank(message = "리뷰 제목은 필수입니다.")
    private String name;

    @NotNull
    @Range(min = 0, max = 5)
    private Byte star;

    @NotBlank(message = "설명은 필수입니다.")
    private String content;

}
