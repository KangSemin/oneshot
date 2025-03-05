package salute.oneshot.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.banner.entity.Banner;
import salute.oneshot.domain.banner.repository.BannerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BannerScheduler {

    private final BannerRepository bannerRepository;
    private final BannerService bannerService;

    @Scheduled(cron = "0 0 0 * * *") // 자정에 배너 활성화
    private void manageActiveBanners() {
        LocalDateTime now = LocalDateTime.now();

        List<Banner> bannersToActivate = bannerRepository.findByStartTimeBetween(
                now.withHour(0).withMinute(0).withSecond(0),
                now.withHour(23).withMinute(59).withSecond(59));
    }

    @Scheduled(cron = "0 0 0 * * *") // 자정에 배너 비활성화
    private void cleanupExpiredBanners() {
        LocalDateTime now = LocalDateTime.now();
        bannerService.deleteBannersByEndTime(now);
    }
}