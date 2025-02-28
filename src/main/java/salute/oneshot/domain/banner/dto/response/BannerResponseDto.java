package salute.oneshot.domain.banner.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.banner.entity.Banner;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannerResponseDto {

    private final Long eventId;
    private final String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endTime;

    public static BannerResponseDto from(Banner banner) {
        return new BannerResponseDto(
                banner.getEvent().getId(),
                banner.getImageUrl(),
                banner.getStartTime(),
                banner.getEndTime());
    }
}
