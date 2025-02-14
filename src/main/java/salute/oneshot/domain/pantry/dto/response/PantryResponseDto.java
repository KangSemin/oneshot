package salute.oneshot.domain.pantry.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.pantry.entity.Pantry;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PantryResponseDto {

    private final Long pantryId;
    private final List<PantryIngrResponseDto> ingredientList;

    public static PantryResponseDto from(Pantry pantry) {

        List<PantryIngrResponseDto> ingrResponseDtoList = pantry.getPantryIngredientList()
            .stream().map(PantryIngrResponseDto::from).toList();

        return new PantryResponseDto(pantry.getId(), ingrResponseDtoList);
    }
}
