package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetMyIngredientReviewSDto {

    private Long userId;
    private Pageable pageable;

    private GetMyIngredientReviewSDto(Long userId, Pageable pageable) {
        this.userId = userId;
        this.pageable = pageable;
    }

    public static GetMyIngredientReviewSDto of (Long userId, Pageable pageable) {
        return new GetMyIngredientReviewSDto(userId, pageable);
    }

}
