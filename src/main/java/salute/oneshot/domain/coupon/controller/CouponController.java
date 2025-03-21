package salute.oneshot.domain.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.GetCpnSDto;
import salute.oneshot.domain.coupon.dto.service.GetUserCpnSDto;
import salute.oneshot.domain.coupon.dto.service.UserCpnSDto;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.global.security.model.CustomUserDetails;

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

    @GetMapping
    public ResponseEntity<ApiResponse<CpnPageResponseDto>> getCoupons(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        Pageable pageable = getPageable(page, size);
        GetCpnSDto serviceDto =
                GetCpnSDto.of(startDate, endDate, pageable);
        CpnPageResponseDto responseDto =
                couponService.getCoupons(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_CPN_LIST_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CpnDetailResponseDto>> getCoupon(
            @PathVariable Long couponId
    ) {
        CpnDetailResponseDto responseDto =
                couponService.getCoupon(couponId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_CPN_SUCCESS,
                        responseDto));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<UserCpnPageResponseDto>> getUserCoupons(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status
    ) {
        Pageable pageable = PageRequest.of(
                    page - 1,
                    size,
                    Sort.by("coupon.endTime").ascending());

        GetUserCpnSDto serviceDto =
                GetUserCpnSDto.of(userDetails.getId(), status, pageable);
        UserCpnPageResponseDto responseDto =
                couponService.getUserCoupons(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_CPN_LIST_SUCCESS,
                        responseDto));
    }

    @GetMapping("/users/{userCouponId}")
    public ResponseEntity<ApiResponse<UserCpnDetailResponseDto>> getUserCoupon(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long userCouponId
    ) {
        UserCpnSDto serviceDto =
                UserCpnSDto.of(userDetails.getId(), userCouponId);
        UserCpnDetailResponseDto responseDto =
                couponService.getUserCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_CPN_SUCCESS,
                        responseDto));
    }

    @GetMapping("/for-event")
    public ResponseEntity<ApiResponse<CpnPageResponseDto>> getCouponsForEvent(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String eventStartDate,
            @RequestParam(required = false) String eventEndDate
    ) {
        Pageable pageable = getPageable(page, size);
        GetCpnSDto serviceDto =
                GetCpnSDto.of(eventStartDate, eventEndDate, pageable);
        CpnPageResponseDto responseDto =
                couponService.getCouponsForEvent(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_CPN_LIST_SUCCESS,
                        responseDto));
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(
                page - 1,
                size,
                Sort.by("endTime").ascending());
    }
}