package salute.oneshot.domain.favorite.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoritePageResponseDto {
    private List<FavoriteResponseDto> favoriteList;
    private int currentPage;
    private int totalPages;
    private boolean hasNext;

    public static FavoritePageResponseDto of(
            Page<FavoriteResponseDto> favoriteList
    ) {
        return new FavoritePageResponseDto(
                favoriteList.getContent(),
                favoriteList.getNumber(),
                favoriteList.getTotalPages(),
                favoriteList.hasNext()
        );
    }
}
