package salute.oneshot.domain.recipeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.CreateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.dto.service.GetAllRecipeReviewSDto;
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

    public RecipeReviewResponseDto getRecipeReview(Long reviewId) {

        RecipeReview recipeReview = recipeReviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        return RecipeReviewResponseDto.from(recipeReview);
    }

    public Page<RecipeReviewResponseDto> getAllRecipeReview(GetAllRecipeReviewSDto sDto) {

        cocktailRepository.findById(sDto.getCocktailId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.RECIPE_NOT_FOUND));

        Page<RecipeReview> recipeReviewPage = recipeReviewRepository.findAllByCocktail_Id(sDto.getCocktailId(), sDto.getPageable());

        Page<RecipeReviewResponseDto> responseDtoPage = recipeReviewPage.map(RecipeReviewResponseDto::from);

        return responseDtoPage;
    }
}
