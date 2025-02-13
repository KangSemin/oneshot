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
import salute.oneshot.domain.ingredientReview.dto.service.GetMyIngredientReviewSDto;
import salute.oneshot.domain.ingredientReview.entity.IngredientReview;
import salute.oneshot.domain.ingredientReview.repository.IngredientReviewRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
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

    public IngrReviewResponseDto getIngredientReview(Long reviewsId) {

        IngredientReview ingredientReview = ingredientReviewRepository.findById(reviewsId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        return IngrReviewResponseDto.from(ingredientReview);
    }
}
