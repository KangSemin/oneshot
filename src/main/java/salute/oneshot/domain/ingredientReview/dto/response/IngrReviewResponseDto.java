package salute.oneshot.domain.ingredientReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngrReviewResponseDto {

    private final IngredientResponseDto ingredient;
    private final UserResponseDto user;
    private final Long reviewId;
    private final Byte star;
    private final String content;

    public static IngrReviewResponseDto from(IngredientResponseDto ingredient, UserResponseDto user, IngredientReview ingredientReview) {
        return new IngrReviewResponseDto(
                ingredient, user, ingredientReview.getId(), ingredientReview.getStar(), ingredientReview.getContent());
    }

}
