package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.Getter;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

@Getter
public class IngrReviewResponseDto {

    private Long reviewId;
    private Byte star;
    private String content;
    private Long userId;

    private IngrReviewResponseDto(Long reviewId, Byte star, String content, Long userId) {
        this.reviewId = reviewId;
        this.star = star;
        this.content = content;
        this.userId = userId;
    }

    public static IngrReviewResponseDto from(IngredientReview ingredientReview) {
        return new IngrReviewResponseDto(
                ingredientReview.getId(), ingredientReview.getStar(),
                ingredientReview.getContent(), ingredientReview.getUser().getId());
    }

}
