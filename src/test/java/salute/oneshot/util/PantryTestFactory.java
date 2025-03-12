package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.pantry.entity.Pantry;

public class PantryTestFactory {

    private static Long userId = 1L ;

    public static Pantry createPantry(){
        Ingredient vodka = IngredientTestFactory.createVodka();
        Pantry pantry = Pantry.of(userId, vodka);

        ReflectionTestUtils.setField(pantry, "id", 1L);

        return pantry;
    }
}
