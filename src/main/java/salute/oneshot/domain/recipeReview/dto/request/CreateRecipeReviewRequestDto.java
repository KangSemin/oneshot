package salute.oneshot.domain.recipeReview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRecipeReviewRequestDto {

    @NotNull
    @Range(min = 0, max = 5)
    private final Byte star;

    @NotBlank(message = "설명은 필수입니다.")
    private final String content;

}
