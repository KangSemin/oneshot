package salute.oneshot.domain.banner.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannerRequestDto {

    private final Long eventId;
    private final String imageUrl;
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
}
