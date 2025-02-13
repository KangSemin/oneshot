package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetAllRecipeReviewSDto {

    public Pageable pageable;
    public Long recipeId;

    public GetAllRecipeReviewSDto(Pageable pageable, Long recipeId) {
        this.pageable = pageable;
        this.recipeId = recipeId;
    }

    public static GetAllRecipeReviewSDto of(Pageable pageable, Long recipeId) {
        return new GetAllRecipeReviewSDto(pageable, recipeId);
    }


}
