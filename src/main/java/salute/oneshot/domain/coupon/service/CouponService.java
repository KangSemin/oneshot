package salute.oneshot.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.*;
import salute.oneshot.domain.coupon.entity.Coupon;
import salute.oneshot.domain.coupon.repository.CouponRepository;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public CpnBriefResponseDto createCoupon(CreateCpnSDto serviceDto) {
        Coupon coupon = Coupon.from(serviceDto);
        couponRepository.save(coupon);

        return CpnBriefResponseDto.from(coupon);
    }
}
