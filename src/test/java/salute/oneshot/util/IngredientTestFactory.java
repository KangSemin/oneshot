package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

public class IngredientTestFactory {


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
}
