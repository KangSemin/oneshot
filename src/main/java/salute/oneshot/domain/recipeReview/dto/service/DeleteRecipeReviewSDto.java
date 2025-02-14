package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteRecipeReviewSDto {

    private Long reviewId;
    private Long userId;

    public static DeleteRecipeReviewSDto of(Long reviewId, Long userId) {
        return new DeleteRecipeReviewSDto(reviewId, userId);
    }

}
