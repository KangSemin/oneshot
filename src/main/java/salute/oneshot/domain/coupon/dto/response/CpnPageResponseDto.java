package salute.oneshot.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CpnPageResponseDto {

    private final List<CpnBriefResponseDto> coupons;
    private final int currentPage;
    private final int totalPages;
    private final boolean hasNext;

    public static CpnPageResponseDto from(
            Page<CpnBriefResponseDto> pageResponseDto
    ) {
        return new CpnPageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext());
    }
}
