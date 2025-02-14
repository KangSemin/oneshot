package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyRecipeReviewSDto {

    private Long userId;
    private Pageable pageable;

    public static GetMyRecipeReviewSDto of (Long userId, Pageable pageable) {
        return new GetMyRecipeReviewSDto(userId, pageable);
    }

}
