package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IngredientTestFactory {

    public static final Long INGREDIENT_ID = 1L;
    public static final String NAME = "라임";
    public static final Double AVB = 0.0;
    public static final String DESCRIPTION = "과일의 한 종류입니다.";
    public static final IngredientCategory CATEGORY = IngredientCategory.OTHER;

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

    public static IngredientResponseDto createIngredientResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<IngredientResponseDto> constructor =
                IngredientResponseDto.class.getDeclaredConstructor(Long.class, String.class, Double.class, String.class, IngredientCategory.class);
        constructor.setAccessible(true);

        return constructor.newInstance(INGREDIENT_ID, NAME, AVB, DESCRIPTION, CATEGORY);
    }
}
