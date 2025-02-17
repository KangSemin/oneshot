package salute.oneshot.domain.recipeReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeReviewResponseDto {

    private final CocktailResponseDto cocktail;
    private final UserResponseDto user;
    private final Long reviewId;
    private final Byte star;
    private final String content;

    public static RecipeReviewResponseDto from(CocktailResponseDto cocktail, UserResponseDto user, RecipeReview recipeReview) {
        return new RecipeReviewResponseDto(
                cocktail, user, recipeReview.getId(), recipeReview.getStar(), recipeReview.getContent());
    }

}

