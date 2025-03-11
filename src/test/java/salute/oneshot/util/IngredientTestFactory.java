package salute.oneshot.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IngredientTestFactory {

    public static final Long INGREDIENT_ID = 1L;
    public static final String NAME = "봄베이 사파이어";
    public static final Double AVB = 47.0d;
    public static final String DESCRIPTION = "주류계의 민트초코";
    public static final IngredientCategory CATEGORY = IngredientCategory.JIN;

    public static Ingredient createVodka() {
        Ingredient ingredient = Ingredient.of(
            "보드카",
            "보드카",
            IngredientCategory.VODKA,
            40.0d,
            "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",1L);
        return ingredient;
    }

    public static Ingredient createJin() {
        Ingredient ingredient = Ingredient.of(
            "봄베이 사파이어",
            "주류계의 민트초코",
            IngredientCategory.JIN,
            47.0d,
            "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",2L);
        return ingredient;
    }

    public static Ingredient createSmirnoff() {
        Ingredient ingredient = Ingredient.of(
            "스미노프 레드",
            "3대 보드카 브랜드중 하나인 스미노프의 보드카",
            IngredientCategory.VODKA,
            40.0d,
            "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",3L);
        return ingredient;
    }

    public static Ingredient createKahlua() {
        Ingredient ingredient = Ingredient.of(
            "깔루아 오리지널",
            "커피 리큐르",
            IngredientCategory.OTHER,
            16.0d,
            "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",4L);
        return ingredient;
    }

    public static Ingredient createMilk() {
        Ingredient ingredient = Ingredient.of(
            "우유",
            "어릴 때 좀 많이 마실 걸",
            IngredientCategory.OTHER,
            0.0d,
            "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",5L);
        return ingredient;
    }


    public static Ingredient createGoldenKeyword(){
        Ingredient ingredient = Ingredient.of(
                "golden",
                "맛있는거",
                IngredientCategory.OTHER,
                0.0d,
                "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",6L);
        return ingredient;

    }

    public static Ingredient createGoldenKeyword2(){
        Ingredient ingredient = Ingredient.of(
                "깔루아",
                "golden --",
                IngredientCategory.OTHER,
                0.0d,
                "대충 이미지 주소"
        );

        ReflectionTestUtils.setField(ingredient,"id",7L);
        return ingredient;

    }


    public static UpdateIngrRequestDto updateIngrRequestDto() {
        MockMultipartFile multipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg",
            new byte[0]);
        return new UpdateIngrRequestDto("깔루아", "맛있는 깔루아", "OTHER", multipartFile, 2.0);
    }

    public static IngredientResponseDto createIngredientResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<IngredientResponseDto> constructor =
                IngredientResponseDto.class.getDeclaredConstructor(Long.class, String.class, Double.class, String.class, IngredientCategory.class);
        constructor.setAccessible(true);

        return constructor.newInstance(INGREDIENT_ID, NAME, AVB, DESCRIPTION, CATEGORY);

    }
}
