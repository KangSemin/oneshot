package salute.oneshot.domain.favorite.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteFavoriteSDto {

    private Long userId;
    private Long favoriteId;

    public static DeleteFavoriteSDto of(Long userId, Long favoriteId) {
        return new DeleteFavoriteSDto(userId, favoriteId);
    }
}
