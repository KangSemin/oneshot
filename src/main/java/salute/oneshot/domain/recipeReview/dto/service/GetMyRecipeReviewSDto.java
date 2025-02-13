package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetMyRecipeReviewSDto {

    private Long userId;
    private Pageable pageable;

    public GetMyRecipeReviewSDto(Long userId, Pageable pageable) {
        this.userId = userId;
        this. pageable = pageable;
    }

    public static GetMyRecipeReviewSDto of (Long userId, Pageable pageable) {
        return new GetMyRecipeReviewSDto(userId, pageable);
    }

}
