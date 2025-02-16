package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

@Getter
@AllArgsConstructor
public class IngrReviewResponseDto {

    private Long reviewId;
    private Byte star;
    private String content;
    private Long userId;

    public static IngrReviewResponseDto from(IngredientReview ingredientReview) {
        return new IngrReviewResponseDto(
                ingredientReview.getId(), ingredientReview.getStar(),
                ingredientReview.getContent(), ingredientReview.getUser().getId());
    }

}
