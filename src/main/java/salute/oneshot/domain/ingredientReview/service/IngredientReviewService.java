package salute.oneshot.domain.ingredientReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;
import salute.oneshot.domain.ingredientReview.dto.response.UserResponseDto;
import salute.oneshot.domain.ingredientReview.dto.service.CreateIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetAllIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetMyIngredientReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.UpdateIngrReviewSDto;
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

        UserResponseDto userResponseDto = UserResponseDto.from(user);
        IngredientResponseDto ingrResponseDto = IngredientResponseDto.from(ingredient);

        return IngrReviewResponseDto.from(ingrResponseDto, userResponseDto, ingredientReview);
    }

    @Transactional
    public IngrReviewResponseDto getIngredientReview(Long reviewsId) {

        IngredientReview ingredientReview = ingredientReviewRepository.findById(reviewsId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        Ingredient ingredient = ingredientReview.getIngredient();
        User user = ingredientReview.getUser();

        UserResponseDto userResponseDto = UserResponseDto.from(user);
        IngredientResponseDto ingrResponseDto = IngredientResponseDto.from(ingredient);

        return IngrReviewResponseDto.from(ingrResponseDto, userResponseDto, ingredientReview);
    }

    @Transactional
    public Page<IngrReviewResponseDto> getMyIngredientReview(GetMyIngredientReviewSDto sDto) {

        Page<IngredientReview> ingredientReviewPage = ingredientReviewRepository
                .findAllByUser_Id(sDto.getUserId(), sDto.getPageable());

        Page<IngrReviewResponseDto> responseDtoPage = ingredientReviewPage.map(ingredientReview -> {

            UserResponseDto userResponseDto = UserResponseDto.from(ingredientReview.getUser());
            IngredientResponseDto ingrResponseDto = IngredientResponseDto.from(ingredientReview.getIngredient());
            return IngrReviewResponseDto.from(ingrResponseDto, userResponseDto, ingredientReview);

        });

        return responseDtoPage;
    }

    @Transactional
    public Page<IngrReviewResponseDto> getAllIngredientReview(GetAllIngrReviewSDto sDto) {

        Ingredient ingredient = ingredientRepository.findById(sDto.getIngredientId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        Page<IngredientReview> ingredientReviewPage = ingredientReviewRepository
                .findAllByIngredient_Id(sDto.getIngredientId(), sDto.getPageable());

        Page<IngrReviewResponseDto> responseDtoPage = ingredientReviewPage.map(ingredientReview -> {

            UserResponseDto userResponseDto = UserResponseDto.from(ingredientReview.getUser());
            IngredientResponseDto ingrResponseDto = IngredientResponseDto.from(ingredientReview.getIngredient());

            return IngrReviewResponseDto.from(ingrResponseDto, userResponseDto, ingredientReview);
        });

        return responseDtoPage;
    }

    @Transactional
    public IngrReviewResponseDto updateIngredientReview(UpdateIngrReviewSDto sDto) {

        IngredientReview ingredientReview = validateUser(sDto.getReviewId(), sDto.getUserId());

        ingredientReview.updateIngredientReview(sDto.getStar(), sDto.getContent());

        return IngrReviewResponseDto.from(ingredientReview);
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
