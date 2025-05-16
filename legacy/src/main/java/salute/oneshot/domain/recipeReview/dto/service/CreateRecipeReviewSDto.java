package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRecipeReviewSDto {

    private final Byte star;
    private final String content;
    private final Long userId;
    private final Long cocktailId;


    public static CreateRecipeReviewSDto of(Byte star, String content, Long userId, Long recipeId) {
        return new CreateRecipeReviewSDto(star, content, userId, recipeId);
    }

}
