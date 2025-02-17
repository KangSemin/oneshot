package salute.oneshot.domain.recipeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.recipeReview.dto.response.CocktailResponseDto;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.response.UserResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.*;
import salute.oneshot.domain.recipeReview.entity.RecipeReview;
import salute.oneshot.domain.recipeReview.repository.RecipeReviewRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.CustomRuntimeException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class RecipeReviewService {

    private final RecipeReviewRepository recipeReviewRepository;
    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;

    @Transactional
    public RecipeReviewResponseDto createRecipeReview(CreateRecipeReviewSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        Cocktail cocktail = cocktailRepository.findById(sDto.getCocktailId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));

        RecipeReview recipeReview = recipeReviewRepository.save(RecipeReview.of(sDto.getStar(), sDto.getContent(), user, cocktail));

        UserResponseDto userResponseDto = UserResponseDto.from(user);
        CocktailResponseDto cocktailResponseDto = CocktailResponseDto.from(cocktail);

        return RecipeReviewResponseDto.from(cocktailResponseDto, userResponseDto, recipeReview);
    }

    @Transactional
    public Page<RecipeReviewResponseDto> getMyRecipeReview(GetMyRecipeReviewSDto sDto) {

        Page<RecipeReview> recipeReviewPage = recipeReviewRepository.findAllByUser_Id(sDto.getUserId(), sDto.getPageable());

        Page<RecipeReviewResponseDto> responseDtoPage = recipeReviewPage.map(recipeReview -> {

            UserResponseDto userResponseDto = UserResponseDto.from(recipeReview.getUser());
            CocktailResponseDto cocktailResponseDto = CocktailResponseDto.from(recipeReview.getCocktail());

            return RecipeReviewResponseDto.from(cocktailResponseDto, userResponseDto, recipeReview);
        });

        return responseDtoPage;
    }

    @Transactional
    public RecipeReviewResponseDto getRecipeReview(Long reviewId) {

        RecipeReview recipeReview = recipeReviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        User user = recipeReview.getUser();
        Cocktail cocktail = recipeReview.getCocktail();

        UserResponseDto userResponseDto = UserResponseDto.from(user);
        CocktailResponseDto cocktailResponseDto = CocktailResponseDto.from(cocktail);

        return RecipeReviewResponseDto.from(cocktailResponseDto, userResponseDto, recipeReview);
    }

    @Transactional
    public Page<RecipeReviewResponseDto> getAllRecipeReview(GetAllRecipeReviewSDto sDto) {

        cocktailRepository.findById(sDto.getCocktailId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));

        Page<RecipeReview> recipeReviewPage = recipeReviewRepository.findAllByCocktail_Id(sDto.getCocktailId(), sDto.getPageable());

        Page<RecipeReviewResponseDto> responseDtoPage = recipeReviewPage.map(recipeReview -> {

            UserResponseDto userResponseDto = UserResponseDto.from(recipeReview.getUser());
            CocktailResponseDto cocktailResponseDto = CocktailResponseDto.from(recipeReview.getCocktail());

            return RecipeReviewResponseDto.from(cocktailResponseDto, userResponseDto, recipeReview);
        });

        return responseDtoPage;
    }

    @Transactional
    public RecipeReviewResponseDto updateRecipeReview(UpdateRecipeReviewSDto sDto) {

        RecipeReview recipeReview = recipeReviewRepository.findById(sDto.getReviewId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        if (!recipeReview.getUser().getId().equals(sDto.getUserId())) {
            throw new CustomRuntimeException(ErrorCode.REVIEW_UPDATE_FORBIDDEN);
        }

        recipeReview.updateRecipeReview(sDto.getStar(), sDto.getContent());

        User user = recipeReview.getUser();
        Cocktail cocktail = recipeReview.getCocktail();

        UserResponseDto userResponseDto = UserResponseDto.from(user);
        CocktailResponseDto cocktailResponseDto = CocktailResponseDto.from(cocktail);

        return RecipeReviewResponseDto.from(cocktailResponseDto, userResponseDto, recipeReview);
    }

    public void deleteRecipeReview(DeleteRecipeReviewSDto sDto) {

        RecipeReview recipeReview = recipeReviewRepository.findById(sDto.getReviewId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        if (!recipeReview.getUser().getId().equals(sDto.getUserId())) {
            throw new CustomRuntimeException(ErrorCode.REVIEW_DELETE_FORBIDDEN);
        }

        recipeReviewRepository.delete(recipeReview);
    }

}
