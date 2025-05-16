package salute.oneshot.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannerPageResponseDto {

    private final List<BannerResponseDto> banners;
    private final int currentPage;
    private final int totalPages;
    private final boolean hasNext;

    public static BannerPageResponseDto from(
            Page<BannerResponseDto> pageResponseDto
    ) {
        return new BannerPageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext());
    }
}
