package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRecipeReviewSDto {

    private Byte star;
    private String content;
    private Long userId;
    private Long cocktailId;


    public static CreateRecipeReviewSDto of(Byte star, String content, Long userId, Long recipeId) {
        return new CreateRecipeReviewSDto(star, content, userId,recipeId);

}
