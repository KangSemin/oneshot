package salute.oneshot.domain.recipeReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeReviewResponseDto {
    private final Long reviewId;
    private final Byte star;
    private final String content;
    private final Long userId;

    public static RecipeReviewResponseDto from(RecipeReview recipeReview) {
        return new RecipeReviewResponseDto(
                recipeReview.getId(), recipeReview.getStar(),
                recipeReview.getContent(), recipeReview.getUser().getId());
    }

}

