package salute.oneshot.domain.favorite.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFavoriteStatusDto {

    private boolean isFavorited;

    public static GetFavoriteStatusDto of(boolean status) {
        return new GetFavoriteStatusDto(status);
    }
}
