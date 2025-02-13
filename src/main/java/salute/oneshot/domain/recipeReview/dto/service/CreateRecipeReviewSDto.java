package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;

@Getter
public class CreateRecipeReviewSDto {

    private Byte star;
    private String content;
    private Long userId;
    private Long cocktailId;

    private CreateRecipeReviewSDto(Byte star, String content, Long userId, Long cocktailId) {
        this.star = star;
        this.content = content;
        this.userId = userId;
        this.cocktailId = cocktailId;

    }

    public static CreateRecipeReviewSDto of(Byte star, String content, Long userId, Long cocktailId) {
        return new CreateRecipeReviewSDto(star, content, userId, cocktailId);
    }
}
