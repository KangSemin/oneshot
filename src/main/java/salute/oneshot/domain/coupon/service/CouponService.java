package salute.oneshot.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.*;
import salute.oneshot.domain.coupon.entity.Coupon;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.repository.CouponRepository;
import salute.oneshot.domain.coupon.repository.RedisEventCouponRepository;
import salute.oneshot.domain.coupon.repository.UserCouponRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final UserRepository userRepository;
    private final RedisEventCouponRepository redisEventCouponRepository;

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

    @Transactional
    public UserCpnBriefResponseDto grantUserCoupon(CreateUserCpnSDto serviceDto) {
        User user = userRepository
                .getReferenceById(serviceDto.getUserId());
        Coupon coupon = couponRepository
                .getReferenceById(serviceDto.getCouponId());
        UserCoupon userCoupon = UserCoupon.of(user, coupon);

        userCouponRepository.save(userCoupon);
        return UserCpnBriefResponseDto.from(userCoupon);
    }

    @Transactional(noRollbackFor = InvalidException.class)
    public UserCpnDetailResponseDto useUserCoupon(UserCpnSDto serviceDto) {
        UserCoupon userCoupon = userCouponRepository.findByIdAndUserId(
                        serviceDto.getUserCouponId(),
                        serviceDto.getUserId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        userCoupon.useUserCoupon();
        return UserCpnDetailResponseDto.of(userCoupon);
    }

    @Transactional(readOnly = true)
    public CpnPageResponseDto getCoupons(GetCpnSDto serviceDto) {
        Page<Coupon> coupons = couponRepository.findCoupons(
                serviceDto.getStarTime(),
                serviceDto.getEndTime(),
                serviceDto.getPageable());

        Page<CpnBriefResponseDto> couponPage =
                coupons.map(CpnBriefResponseDto::from);

        return CpnPageResponseDto.from(couponPage);
    }

    @Transactional(readOnly = true)
    public CpnDetailResponseDto getCoupon(Long couponId) {
        Coupon coupon = getCouponById(couponId);

        return CpnDetailResponseDto.from(coupon);
    }

    @Transactional(readOnly = true)
    public UserCpnPageResponseDto getUserCoupons(GetUserCpnSDto serviceDto) {
        Page<UserCoupon> userCoupons = userCouponRepository.findUserCoupons(
                serviceDto.getUserId(),
                serviceDto.getStatus(),
                serviceDto.getPageable());

        Page<UserCpnBriefResponseDto> UserCouponPage =
                userCoupons.map(UserCpnBriefResponseDto::from);

        return UserCpnPageResponseDto.from(UserCouponPage);
    }

    @Transactional(readOnly = true)
    public UserCpnDetailResponseDto getUserCoupon(UserCpnSDto serviceDto) {
        UserCoupon userCoupon = userCouponRepository.findByIdAndUserId(
                        serviceDto.getUserCouponId(),
                        serviceDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        return UserCpnDetailResponseDto.of(userCoupon);
    }

    @Transactional(readOnly = true)
    public CpnPageResponseDto getCouponsForEvent(GetCpnSDto serviceDto) {
        Page<Coupon> coupons = couponRepository.findCouponsForEvent(
                serviceDto.getStarTime(),
                serviceDto.getEndTime(),
                serviceDto.getPageable());

        Page<CpnBriefResponseDto> couponPage =
                coupons.map(CpnBriefResponseDto::from);

        List<String> couponIds = couponPage.getContent().stream()
                .map(dto -> String.valueOf(dto.getId()))
                .collect(Collectors.toList());

        redisEventCouponRepository.saveCouponIds(couponIds);

        return CpnPageResponseDto.from(couponPage);

    }


    private Coupon getCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.COUPON_NOT_FOUND));
    }
}
