package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteIngrReviewSDto {

    private Long reviewId;
    private Long userId;

    public static DeleteIngrReviewSDto of(Long reviewId, Long userId) {
        return new DeleteIngrReviewSDto(reviewId, userId);
    }
}