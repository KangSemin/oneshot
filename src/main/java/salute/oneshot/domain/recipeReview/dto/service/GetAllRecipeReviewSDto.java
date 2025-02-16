package salute.oneshot.domain.recipeReview.dto.service;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetAllRecipeReviewSDto {

    public Pageable pageable;
    public Long cocktailId;

    public GetAllRecipeReviewSDto(Pageable pageable, Long cocktailId) {
        this.pageable = pageable;
        this.cocktailId = cocktailId;
    }

    public static GetAllRecipeReviewSDto of(Pageable pageable, Long cocktailId) {
        return new GetAllRecipeReviewSDto(pageable, cocktailId);
    }


}
