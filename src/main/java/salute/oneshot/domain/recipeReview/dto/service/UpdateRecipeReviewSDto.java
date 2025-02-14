package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRecipeReviewSDto {

    private Long reviewId;
    private Byte star;
    private String content;
    private Long userId;

    public static UpdateRecipeReviewSDto of(Long reviewId, Byte star, String content, Long userId) {
        return new UpdateRecipeReviewSDto(reviewId, star, content, userId);
    }

}

