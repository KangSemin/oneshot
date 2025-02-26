package salute.oneshot.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.*;
import salute.oneshot.domain.coupon.entity.Coupon;
import salute.oneshot.domain.coupon.repository.CouponRepository;
import salute.oneshot.global.exception.NotFoundException;

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

    @Transactional
    public CpnDetailResponseDto updateCoupon(UpdateCpnSDto serviceDto) {
        Coupon coupon = getCouponById(serviceDto.getCouponId());
        coupon.updateCoupon(serviceDto);

        return CpnDetailResponseDto.from(coupon);
    }

    @Transactional
    public Long deleteCoupon(Long couponId) {
        if (couponRepository.deleteCouponById(couponId) == 1) {
            return couponId;
        }
        throw new NotFoundException(ErrorCode.COUPON_NOT_FOUND);
    }

    private Coupon getCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.COUPON_NOT_FOUND));
    }
}
