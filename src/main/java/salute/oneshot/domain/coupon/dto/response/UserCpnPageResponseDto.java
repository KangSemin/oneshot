package salute.oneshot.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCpnPageResponseDto {

    private final List<UserCpnBriefResponseDto> userCoupons;
    private final int currentPage;
    private final int totalPages;
    private final boolean hasNext;

    public static UserCpnPageResponseDto from(
            Page<UserCpnBriefResponseDto> pageResponseDto
    ) {
        return new UserCpnPageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext());
    }
}
