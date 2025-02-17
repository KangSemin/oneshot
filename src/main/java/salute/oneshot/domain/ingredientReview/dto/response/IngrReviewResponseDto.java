package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngrReviewResponseDto {

    private final Long reviewId;
    private final Byte star;
    private final String content;
    private final Long userId;

    public static IngrReviewResponseDto from(IngredientReview ingredientReview) {
        return new IngrReviewResponseDto(
                ingredientReview.getId(), ingredientReview.getStar(),
                ingredientReview.getContent(), ingredientReview.getUser().getId());
    }

}
