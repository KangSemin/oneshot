package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;

@Getter
public class GetRecipeReviewSDto {

    private Long recipeId;
    private Long reviewId;

    private GetRecipeReviewSDto(Long recipeId, Long reviewId) {
        this.reviewId = recipeId;
        this.recipeId = reviewId;
    }

    public static GetRecipeReviewSDto of(Long recipeId, Long reviewId) {
        return new GetRecipeReviewSDto(recipeId, reviewId);
    }
}
