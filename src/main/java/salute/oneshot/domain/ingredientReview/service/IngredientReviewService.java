package salute.oneshot.domain.ingredientReview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.service.CreateIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.DeleteIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetMyIngredientReviewSDto;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;
import salute.oneshot.domain.ingredientReview.repository.IngredientReviewRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.CustomRuntimeException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class IngredientReviewService {

    private final IngredientReviewRepository ingredientReviewRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Transactional
    public IngrReviewResponseDto createIngredientReview(CreateIngrReviewSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        Ingredient ingredient = ingredientRepository.findById(sDto.getIngredientId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        IngredientReview ingredientReview = ingredientReviewRepository.save(IngredientReview.of(sDto.getStar(), sDto.getContent(), user, ingredient));

        return IngrReviewResponseDto.from(ingredientReview);
    }

    public Page<IngrReviewResponseDto> getMyIngredientReview(GetMyIngredientReviewSDto sDto) {

        Page<IngredientReview> ingredientReviewPage = ingredientReviewRepository
                .findAllByUser_Id(sDto.getUserId(), sDto.getPageable());

        Page<IngrReviewResponseDto> responseDtoPage = ingredientReviewPage.map(IngrReviewResponseDto::from);

        return responseDtoPage;
    }

    public void deleteIngredientReview(DeleteIngrReviewSDto sDto) {

        IngredientReview ingredientReview = validateUser(sDto.getReviewId(), sDto.getUserId());

        ingredientReviewRepository.delete(ingredientReview);
    }

    private IngredientReview validateUser(Long reviewId, Long userId) {
        IngredientReview ingredientReview = ingredientReviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        if (!ingredientReview.getUser().getId().equals(userId)) {
            throw new CustomRuntimeException(ErrorCode.REVIEW_DELETE_FORBIDDEN);
        }

        return ingredientReview;
    }
}
