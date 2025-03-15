package salute.oneshot.domain.banner.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
            Long eventId,
            String imageUrl,
            String startDate,
            String startTime,
            String endDate,
            String endTime
    ) {
        return new UpdateBannerSDto(
                bannerId,
                eventId,
                imageUrl,
                DateTimeUtil.parseStartDateTime(
                        startDate,
                        startTime),
                DateTimeUtil.parseEndDateTime(
                        endDate,
                        endTime));
    }
}
