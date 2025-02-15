package salute.oneshot.domain.pantry.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemoveIngrFromPantrySDto {

    private Long userId;
    private Long ingredientId;

    public static RemoveIngrFromPantrySDto of(Long userId ,Long ingredientId) {
        return new RemoveIngrFromPantrySDto(userId,ingredientId);
    }

}
