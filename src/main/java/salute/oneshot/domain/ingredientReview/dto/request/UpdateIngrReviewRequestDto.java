package salute.oneshot.domain.ingredientReview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateIngrReviewRequestDto {

    @NotNull
    @Range(min = 0, max = 5)
    private final Byte star;

    @NotBlank
    private final String content;

}