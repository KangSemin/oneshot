package salute.oneshot.domain.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.UserCpnSDto;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/users/{userCouponId}")
    public ResponseEntity<ApiResponse<UserCpnDetailResponseDto>> useUserCoupon(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long userCouponId
    ) {
        UserCpnSDto serviceDto =
                UserCpnSDto.of(userDetails.getId(), userCouponId);
        UserCpnDetailResponseDto responseDto =
                couponService.useUserCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.USE_USER_CPN_SUCCESS,
                        responseDto));
    }
}
