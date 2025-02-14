package salute.oneshot.domain.favorite.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoritePageResponseDto {
    private List<FavoriteResponseDto> favorites;
    private int currentPage;
    private int totalPages;
    private boolean hasNext;

    public static FavoritePageResponseDto from(
            Page<FavoriteResponseDto> pageResponseDto
    ) {
        return new FavoritePageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext()
        );
    }
}
