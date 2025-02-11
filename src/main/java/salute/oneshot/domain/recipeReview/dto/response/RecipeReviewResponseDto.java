package salute.oneshot.domain.recipeReview.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeReviewResponseDto {
    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long recipeId;
    private final Byte star;

}

