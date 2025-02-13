package salute.oneshot.domain.favorite.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoriteStatusDto {

    private boolean isFavorited;

    public static FavoriteStatusDto of(boolean status) {
        return new FavoriteStatusDto(status);
    }
}
