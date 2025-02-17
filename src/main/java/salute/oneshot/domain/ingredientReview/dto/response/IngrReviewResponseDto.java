package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

@Getter
@AllArgsConstructor
public class IngrReviewResponseDto {

    private IngredientResponseDto ingredient;
    private UserResponseDto user;
    private Long reviewId;
    private Byte star;
    private String content;

    public static IngrReviewResponseDto from(IngredientResponseDto ingredient, UserResponseDto user, IngredientReview ingredientReview) {
        return new IngrReviewResponseDto(
                ingredient, user, ingredientReview.getId(), ingredientReview.getStar(),
                ingredientReview.getContent());
    }

}
