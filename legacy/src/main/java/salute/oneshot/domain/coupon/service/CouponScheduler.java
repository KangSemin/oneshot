package salute.oneshot.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.coupon.repository.UserCouponRepository;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponScheduler {

    private final UserCouponRepository userCouponRepository;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정(00:00)에 실행, 만료된 사용자 쿠폰을 처리
    @Transactional
    public void expireUserCoupons() {
        LocalDateTime now = LocalDateTime.now();
        int updatedCount =
                userCouponRepository.updateExpiredUserCoupons(now);
        log.info("Expired {} user coupons", updatedCount);
    }
}