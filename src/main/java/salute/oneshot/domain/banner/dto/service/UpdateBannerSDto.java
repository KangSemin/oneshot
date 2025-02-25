package salute.oneshot.domain.banner.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBannerSDto {

    private final Long bannerId;
    private final Long eventId;
    private final String imageUrl;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public static UpdateBannerSDto of(
            Long bannerId,
            BannerRequestDto requestDto
    ) {
        return new UpdateBannerSDto(
                bannerId,
                requestDto.getEventId(),
                requestDto.getImageUrl(),
                DateTimeUtil.parseStartDateTime(
                        requestDto.getStartDate(),
                        requestDto.getStartTime()),
                DateTimeUtil.parseEndDateTime(
                        requestDto.getEndDate(),
                        requestDto.getEndTime()));
    }
}
