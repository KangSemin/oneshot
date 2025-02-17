package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteIngrReviewSDto {

    private final Long reviewId;
    private final Long userId;

    public static DeleteIngrReviewSDto of(Long reviewId, Long userId) {
        return new DeleteIngrReviewSDto(reviewId, userId);
    }
}