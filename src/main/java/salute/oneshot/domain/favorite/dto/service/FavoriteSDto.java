package salute.oneshot.domain.favorite.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoriteSDto {

    private Long userId;
    private Long cocktailId;

    public static FavoriteSDto of(Long userId, Long cocktailId) {
        return new FavoriteSDto(userId, cocktailId);
    }
}
