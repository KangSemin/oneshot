package salute.oneshot.domain.pantry.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPantryIngredientSDto {

    private Long userId;
    private Long ingredientId;

    public static AddPantryIngredientSDto of(Long userId ,Long ingredientId) {
        return new AddPantryIngredientSDto(userId,ingredientId);
    }

}
