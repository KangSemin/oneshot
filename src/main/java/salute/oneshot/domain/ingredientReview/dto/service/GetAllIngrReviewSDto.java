package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllIngrReviewSDto {

    private Long ingredientId;
    private Pageable pageable;

    public static GetAllIngrReviewSDto of(Long ingredientId, Pageable pageable) {
        return new GetAllIngrReviewSDto(ingredientId, pageable);
    }

}