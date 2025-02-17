package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyIngredientReviewSDto {

    private final Long userId;
    private final Pageable pageable;

    public static GetMyIngredientReviewSDto of (Long userId, Pageable pageable) {
        return new GetMyIngredientReviewSDto(userId, pageable);
    }

}
