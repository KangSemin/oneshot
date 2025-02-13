package salute.oneshot.domain.favorite.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFavoriteSDto {

    private Long recipeId;
    private Long userId;

    public static CreateFavoriteSDto of(Long recipeId, Long userId) {
        return new CreateFavoriteSDto(recipeId, userId);
    }
}
