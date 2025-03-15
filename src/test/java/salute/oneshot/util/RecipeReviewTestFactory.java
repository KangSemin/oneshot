package salute.oneshot.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.recipeReview.dto.request.CreateRecipeReviewRequestDto;
import salute.oneshot.domain.recipeReview.dto.request.UpdateRecipeRequestDto;
import salute.oneshot.domain.recipeReview.dto.response.CocktailResponseDto;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.response.UserResponseDto;
import salute.oneshot.domain.user.entity.UserRole;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RecipeReviewTestFactory {

    public static final Long RECIPE_REVIEW_ID = 1L;
    public static final Byte STAR = 5;
    public static final String CONTENT = "맛있어요!";

    public static RecipeReviewResponseDto createRecipeReviewResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<RecipeReviewResponseDto> constructor =
                RecipeReviewResponseDto.class.getDeclaredConstructor(CocktailResponseDto.class, UserResponseDto.class, Long.class, Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(createCocktailResponseDto(), createUserResponseDto(), RECIPE_REVIEW_ID, STAR, CONTENT);
    }

    public static Page<RecipeReviewResponseDto> createRecipeReviewResponseDtoPage() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<RecipeReviewResponseDto> recipeReviewResponseDtoList = List.of(createRecipeReviewResponseDto());
        return new PageImpl<>(recipeReviewResponseDtoList);
    }

    public static CreateRecipeReviewRequestDto createCreateRecipeReviewRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateRecipeReviewRequestDto> constructor =
                CreateRecipeReviewRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(STAR, CONTENT);
    }

    public static UpdateRecipeRequestDto createUpdateRecipeRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateRecipeRequestDto> constructor =
                UpdateRecipeRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(STAR, CONTENT);
    }

    // TODO: 도메인 분리 필요
    public static UserResponseDto createUserResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UserResponseDto> constructor =
                UserResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, UserRole.class);
        constructor.setAccessible(true);

        return constructor.newInstance(UserTestFactory.USER_ID, UserTestFactory.EMAIL, UserTestFactory.NICKNAME, UserTestFactory.ROLE_USER);
    }

    // TODO: 도메인 분리 필요
    public static CocktailResponseDto createCocktailResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CocktailResponseDto> constructor =
                CocktailResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, String.class, RecipeType.class, Integer.class, Long.class);
        constructor.setAccessible(true);

        return constructor.newInstance(CocktailTestFactory.COCKTAIL_ID, CocktailTestFactory.NAME, CocktailTestFactory.DESCRIPTION, CocktailTestFactory.RECIPE, CocktailTestFactory.TYPE, CocktailTestFactory.LIKE_COUNT, UserTestFactory.USER_ID);
    }
}
