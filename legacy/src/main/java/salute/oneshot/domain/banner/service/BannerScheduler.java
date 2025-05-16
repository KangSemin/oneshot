package salute.oneshot.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.banner.repository.BannerRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class BannerScheduler {

    private final BannerRepository bannerRepository;

    @Scheduled(cron = "0 0 17,0 * * *") // 17시, 자정에 배너 활성화
    private void manageActiveBanners() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);

        bannerRepository.activateBanner(now, endOfDay);
    }

    @Scheduled(cron = "0 0 19,0 * * *") // 19시, 자정에 배너 비활성화
    private void cleanupExpiredBanners() {
        LocalDateTime now = LocalDateTime.now();

        bannerRepository.deactivateBanner(now);
    }
}