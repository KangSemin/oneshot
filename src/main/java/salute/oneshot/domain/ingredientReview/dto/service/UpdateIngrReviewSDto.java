package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateIngrReviewSDto {

    private Long reviewId;
    private Byte star;
    private String content;
    private Long userId;

    public static UpdateIngrReviewSDto of(Long reviewId, Byte star, String content, Long userId) {
        return new UpdateIngrReviewSDto(reviewId, star, content, userId);
    }

}