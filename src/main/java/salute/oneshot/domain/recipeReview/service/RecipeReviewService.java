package salute.oneshot.domain.recipeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.recipe.entity.Recipe;
import salute.oneshot.domain.recipe.repository.RecipeRepository;
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
    private final RecipeRepository recipeRepository;

    public RecipeReviewResponseDto createRecipeReview(CreateRecipeReviewSDto sDto) {

        Recipe recipe = recipeRepository.findById(sDto.getRecipeId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.RECIPE_NOT_FOUND));

        RecipeReview recipeReview = recipeReviewRepository.save(RecipeReview.of(sDto.getStar(),sDto.getContent(), recipe));

        return RecipeReviewResponseDto.from(recipeReview);
    }

    public Page<RecipeReviewResponseDto> getAllRecipeReview(GetAllRecipeReviewSDto sDto) {

        recipeRepository.findById(sDto.getRecipeId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.RECIPE_NOT_FOUND));

        Page<RecipeReview> recipeReviewPage = recipeReviewRepository.findAllByRecipe_Id(sDto.getRecipeId(), sDto.getPageable());

        Page<RecipeReviewResponseDto> responseDtoPage = recipeReviewPage.map(RecipeReviewResponseDto::from);

        return responseDtoPage;
    }
}
