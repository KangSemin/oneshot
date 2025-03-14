package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.domain.banner.entity.Banner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static BannerRequestDto createBannerRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(1);
        LocalDateTime endTime = now.plusDays(7);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Constructor<BannerRequestDto> bannerCont = BannerRequestDto.class
                .getDeclaredConstructor(
                        Long.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class);
        bannerCont.setAccessible(true);

        return bannerCont.newInstance(
                BANNER_ID,
                IMAGE_URL,
                startTime.format(dateFormatter),
                startTime.format(timeFormatter),
                endTime.format(dateFormatter),
                endTime.format(timeFormatter));
    }

    public static BannerRequestDto createInvalidBannerRequestDto()
            throws InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {
        Constructor<BannerRequestDto> bannerCont = BannerRequestDto.class
                .getDeclaredConstructor(
                        Long.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class);
        bannerCont.setAccessible(true);

        return bannerCont.newInstance(
                BANNER_ID,
                IMAGE_URL,
                START_DATE,
                START_TIME,
                END_DATE,
                END_DATE);
    }
}
