package salute.oneshot.domain.pantry.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.pantry.entity.Pantry;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PantryResponseDto {

    private final Long userId;
    private final Long pantryId;
    private final IngrResponseDto ingredient;

    public static PantryResponseDto from(Pantry pantry) {

        IngrResponseDto ingredient = IngrResponseDto.from(pantry.getIngredient());
        return new PantryResponseDto(pantry.getUserId(), pantry.getId(), ingredient);
    }
}
