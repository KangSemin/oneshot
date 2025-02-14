package salute.oneshot.domain.recipeReview.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetAllRecipeReviewSDto {

    public Pageable pageable;
    public Long cocktailId;

    public static GetAllRecipeReviewSDto of(Pageable pageable, Long cocktailId) {
        return new GetAllRecipeReviewSDto(pageable, cocktailId);
    }


}
