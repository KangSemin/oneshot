package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;

@Getter
public class DeleteRecipeReviewSDto {

    private Long reviewId;
    private Long userId;

    public DeleteRecipeReviewSDto(Long reviewId, Long userId) {
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public static DeleteRecipeReviewSDto of(Long reviewId, Long userId) {
        return new DeleteRecipeReviewSDto(reviewId, userId);
    }

}
