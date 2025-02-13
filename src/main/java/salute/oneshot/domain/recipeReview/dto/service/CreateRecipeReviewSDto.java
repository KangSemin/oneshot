package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;

@Getter
public class CreateRecipeReviewSDto {

    private Byte star;
    private String content;
    private Long userId;
    private Long recipeId;

    private CreateRecipeReviewSDto(Byte star, String content, Long userId, Long recipeId) {
        this.star = star;
        this.content = content;
        this.userId = userId;
        this.recipeId = recipeId;

    }

    public static CreateRecipeReviewSDto of(Byte star, String content, Long userId, Long recipeId) {
        return new CreateRecipeReviewSDto(star, content, userId,recipeId);
    }
}
