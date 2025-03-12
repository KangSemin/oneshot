package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.domain.banner.entity.Banner;

import java.time.LocalDateTime;

public class BannerTestFactory {

    public static final Long BANNER_ID = 1L;
    public static final String IMAGE_URL = "https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1";
    public static final String START_DATE = "2025-03-08";
    public static final String START_TIME = "18:00";
    public static final String END_DATE = "2025-03-09";
    public static final String END_TIME = "19:00";
    public static final LocalDateTime START_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-09T18:00:00");
    public static final LocalDateTime END_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-11T00:00:00");

    public static Banner createBanner() {
        Banner banner = Banner.of(
                EventTestFactory.createEvent(),
                IMAGE_URL,
                START_LOCAL_DATE_TIME,
                END_LOCAL_DATE_TIME);

        ReflectionTestUtils.setField(banner, "id", BANNER_ID);

        return banner;
    }

    public static Banner createBanner2() {
        Banner banner = Banner.of(
                EventTestFactory.createEvent2(),
                "https://via.placeholder.com/1200x300/33FF57/000000?text=Limited+Time+Offer",
                LocalDateTime.parse("2025-03-10T18:00:00"),
                LocalDateTime.parse("2025-03-12T18:00:00"));

        ReflectionTestUtils.setField(banner, "id", 2L);

        return banner;
    }

    public static Banner createBanner3() {
        Banner banner = Banner.of(
                EventTestFactory.createEvent3(),
                "https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion",
                LocalDateTime.parse("2025-03-11T18:00:00"),
                LocalDateTime.parse("2025-03-12T18:00:00"));

        ReflectionTestUtils.setField(banner, "id", 3L);

        return banner;
    }

    public static BannerRequestDto createBannerRequestDto() {
        return BannerRequestDto.of(
                BANNER_ID,
                IMAGE_URL,
                START_DATE,
                START_TIME,
                END_DATE,
                END_TIME);
    }

    public static BannerRequestDto createInvalidBannerRequestDto() {
        return BannerRequestDto.of(
                BANNER_ID,
                IMAGE_URL,
                START_DATE,
                START_TIME,
                END_DATE,
                END_DATE);
    }
}
