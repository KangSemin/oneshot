package salute.oneshot.domain.cocktail.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum RecipeType {
    OFFICAIL,
    CUSTOM;

    public static RecipeType of(String type) {
        return Arrays.stream(RecipeType.values())
                .filter(r -> r.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("유효하지않은 recipeType입니다"));
    }
}
