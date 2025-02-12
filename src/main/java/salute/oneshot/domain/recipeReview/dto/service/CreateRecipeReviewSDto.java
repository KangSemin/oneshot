package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;

@Getter
public class CreateRecipeReviewSDto {

    private String name;
    private Byte star;
    private String content;
    private Long recipeId;

    private CreateRecipeReviewSDto(String name, Byte star, String content, Long recipeId) {
        this.name = name;
        this.star = star;
        this.content = content;
        this.recipeId = recipeId;

    }

    public static CreateRecipeReviewSDto of(String name, Byte star, String content, Long recipeId) {
        return new CreateRecipeReviewSDto(name, star, content,recipeId);
    }
}
