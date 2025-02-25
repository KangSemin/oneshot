package salute.oneshot.domain.banner.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.global.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannerSDto {

    private final Long eventId;
    private final String imageUrl;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public static BannerSDto from(BannerRequestDto requestDto) {
        return new BannerSDto(
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
