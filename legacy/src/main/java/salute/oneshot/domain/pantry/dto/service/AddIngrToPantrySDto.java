package salute.oneshot.domain.pantry.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddIngrToPantrySDto {

    private Long userId;
    private Long ingredientId;

    public static AddIngrToPantrySDto of(Long userId ,Long ingredientId) {
        return new AddIngrToPantrySDto(userId,ingredientId);
    }

}
