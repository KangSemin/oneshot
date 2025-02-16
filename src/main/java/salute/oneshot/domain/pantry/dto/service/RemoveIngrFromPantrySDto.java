package salute.oneshot.domain.pantry.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemoveIngrFromPantrySDto {

    private Long userId;
    private List<Long> ingredientIds;

    public static RemoveIngrFromPantrySDto of(Long userId ,List<Long> ingredientIds) {
        return new RemoveIngrFromPantrySDto(userId,ingredientIds);
    }

}
