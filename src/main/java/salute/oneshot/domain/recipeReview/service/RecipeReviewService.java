package salute.oneshot.domain.recipeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.CreateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;
import salute.oneshot.domain.recipeReview.repository.RecipeReviewRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class RecipeReviewService {

    private final RecipeReviewRepository recipeReviewRepository;
    private final CocktailRepository cocktailRepository;

    public RecipeReviewResponseDto createRecipeReview(CreateRecipeReviewSDto sDto) {

        Cocktail cocktail = cocktailRepository.findById(sDto.getRecipeId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));

        RecipeReview recipeReview = recipeReviewRepository.save(RecipeReview.of(sDto.getStar(),sDto.getContent(), cocktail));

        return RecipeReviewResponseDto.from(recipeReview);
    }
}
