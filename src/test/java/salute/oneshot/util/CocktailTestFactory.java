package salute.oneshot.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;

public class CocktailTestFactory {


    public static Cocktail createBlackRussian() {
        Cocktail cocktail = Cocktail.of(
            "블랙 러시안",
            "보드카와 깔루아로 만드는 칵테일",
            "1.칠링한 온더락 글라스에 재료들을 붓는다.\n2.젓는다.",
            RecipeType.CUSTOM,
            UserTestFactory.createUser(),
            new ArrayList<>(),
            "대충 이미지 주소"
        );

        cocktail.getIngredientList().addAll(
            List.of(
                CocktailIngredient.of(cocktail,IngredientTestFactory.createVodka(),"60ml"),
                CocktailIngredient.of(cocktail,IngredientTestFactory.createKahlua(),"20ml")
            )
        );

        ReflectionTestUtils.setField(cocktail,"id",1L);
        return cocktail;
    }

    public static Cocktail createWhiteRussian() {
        Cocktail cocktail = Cocktail.of(
            "화이트 러시안",
            "블랙 러시안에 우유를 더한 칵테일",
            "1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\n2.우유 혹은 생크림을 조심스레 붓는다.\n3.살짝 저어준다.",
            RecipeType.OFFICIAL,
            UserTestFactory.createUser(),
            new ArrayList<>(),
            "대충 이미지 주소"
        );
        cocktail.getIngredientList().addAll(
            List.of(
                CocktailIngredient.of(cocktail,IngredientTestFactory.createVodka(),"60ml"),
                CocktailIngredient.of(cocktail,IngredientTestFactory.createKahlua(),"20ml"),
                CocktailIngredient.of(cocktail,IngredientTestFactory.createMilk(),"30ml")
            )
        );

        ReflectionTestUtils.setField(cocktail,"id",2L);
        return cocktail;
    }

    public static Cocktail createKahluaMilk() {
        Cocktail cocktail = Cocktail.of(
            "깔루아 밀크",
            "달달구리한 커피 칵테일",
            "1.칠링한 온더락 글라스에 재료들을 붓는다.\n2.젓는다.",
            RecipeType.OFFICIAL,
            UserTestFactory.createUser(),
            new ArrayList<>(),
            "대충 이미지 주소"
        );
        cocktail.getIngredientList().addAll(
            List.of(
                CocktailIngredient.of(cocktail,IngredientTestFactory.createKahlua(),"30ml"),
                CocktailIngredient.of(cocktail,IngredientTestFactory.createMilk(),"105ml")
            )
        );

        ReflectionTestUtils.setField(cocktail,"id",3L);
        return cocktail;
    }
}
