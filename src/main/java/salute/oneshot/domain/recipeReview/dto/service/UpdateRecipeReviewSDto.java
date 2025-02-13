package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;

@Getter
public class UpdateRecipeReviewSDto {

    private Long reviewId;
    private Byte star;
    private String content;
    private Long userId;

    private UpdateRecipeReviewSDto(Long reviewId, Byte star, String content, Long userId) {
        this.reviewId = reviewId;
        this.star = star;
        this.content = content;
        this.userId = userId;

    }

    public static UpdateRecipeReviewSDto of(Long reviewId, Byte star, String content, Long userId) {
        return new UpdateRecipeReviewSDto(reviewId, star, content, userId);
    }
}

