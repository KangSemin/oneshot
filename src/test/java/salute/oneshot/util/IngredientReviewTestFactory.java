package salute.oneshot.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredientReview.dto.request.CreateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.request.UpdateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;
import salute.oneshot.domain.ingredientReview.dto.response.UserResponseDto;
import salute.oneshot.domain.user.entity.UserRole;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class IngredientReviewTestFactory {

    public static final Long INGREDIENT_REVIEW_ID = 1L;
    public static final Byte STAR = 1;
    public static final String CONTENT = "최악의 재료";

    public static CreateIngrReviewRequestDto createCreateIngrReviewRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateIngrReviewRequestDto> constructor =
                CreateIngrReviewRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(STAR, CONTENT);
    }

    public static IngrReviewResponseDto createIngrReviewResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<IngrReviewResponseDto> constructor =
                IngrReviewResponseDto.class.getDeclaredConstructor(IngredientResponseDto.class, UserResponseDto.class, Long.class, Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(createIngredientResponseDto(), createUserResponseDto(), INGREDIENT_REVIEW_ID, STAR, CONTENT);
    }

    public static Page<IngrReviewResponseDto> createIngrReviewResponseDtoPage() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<IngrReviewResponseDto> ingrReviewResponseDtoPage = new ArrayList<>();
        ingrReviewResponseDtoPage.add(createIngrReviewResponseDto());

        return new PageImpl<>(ingrReviewResponseDtoPage);
    }

    public static UpdateIngrReviewRequestDto createUpdateIngrReviewRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateIngrReviewRequestDto> constructor =
                UpdateIngrReviewRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(STAR, CONTENT);
    }
//    public static final Byte STAR = 5;
//    public static final String CONTENT = "맛있어요!";
//
//    public static RecipeReviewResponseDto createRecipeReviewResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<RecipeReviewResponseDto> constructor =
//                RecipeReviewResponseDto.class.getDeclaredConstructor(CocktailResponseDto.class, UserResponseDto.class, Long.class, Byte.class, String.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(createCocktailResponseDto(), createUserResponseDto(), RECIPE_REVIEW_ID, STAR, CONTENT);
//    }
//
//    public static Page<RecipeReviewResponseDto> createRecipeReviewResponseDtoPage() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        List<RecipeReviewResponseDto> recipeReviewResponseDtoList = new ArrayList<>();
//        recipeReviewResponseDtoList.add(createRecipeReviewResponseDto());
//        return new PageImpl<>(recipeReviewResponseDtoList);
//    }
//
//    public static CreateRecipeReviewRequestDto createCreateRecipeReviewRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<CreateRecipeReviewRequestDto> constructor =
//                CreateRecipeReviewRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(STAR, CONTENT);
//    }
//
//    public static UpdateRecipeRequestDto createUpdateRecipeRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<UpdateRecipeRequestDto> constructor =
//                UpdateRecipeRequestDto.class.getDeclaredConstructor(Byte.class, String.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(STAR, CONTENT);
//    }
//
//    // TODO: 도메인 분리 필요
//    public static UserResponseDto createUserResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<UserResponseDto> constructor =
//                UserResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, UserRole.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(UserTestFactory.USER_ID, UserTestFactory.EMAIL, UserTestFactory.NICKNAME, UserTestFactory.ROLE_USER);
//    }
//
//    // TODO: 도메인 분리 필요
//    public static CocktailResponseDto createCocktailResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        List<IngredientResponseDto> ingredientList = new ArrayList<>();
//        ingredientList.add(IngredientTestFactory.createIngredientResponseDto());
//
//        Constructor<CocktailResponseDto> constructor =
//                CocktailResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, String.class, RecipeType.class, Integer.class, Long.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(CocktailTestFactory.COCKTAIL_ID, CocktailTestFactory.NAME, CocktailTestFactory.DESCRIPTION, CocktailTestFactory.RECIPE, CocktailTestFactory.TYPE, CocktailTestFactory.LIKE_COUNT, UserTestFactory.USER_ID);

//    }

    // TODO: 도메인 분리 필요
    public static UserResponseDto createUserResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UserResponseDto> constructor =
                UserResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, UserRole.class);
        constructor.setAccessible(true);

        return constructor.newInstance(UserTestFactory.USER_ID, UserTestFactory.EMAIL, UserTestFactory.NICKNAME, UserTestFactory.ROLE_USER);
    }

    // TODO: 도메인 분리 필요
    public static IngredientResponseDto createIngredientResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<IngredientResponseDto> constructor =
                IngredientResponseDto.class.getDeclaredConstructor(Long.class, String.class, Double.class, String.class, IngredientCategory.class);
        constructor.setAccessible(true);

        return constructor.newInstance(IngredientTestFactory.INGREDIENT_ID, IngredientTestFactory.NAME, IngredientTestFactory.AVB, IngredientTestFactory.DESCRIPTION, IngredientTestFactory.CATEGORY);
    }
}
