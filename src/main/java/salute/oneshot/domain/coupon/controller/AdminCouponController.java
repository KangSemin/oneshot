package salute.oneshot.domain.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.domain.coupon.dto.response.CpnBriefResponseDto;
import salute.oneshot.domain.coupon.dto.response.CpnDetailResponseDto;
import salute.oneshot.domain.coupon.dto.service.CreateCpnSDto;
import salute.oneshot.domain.coupon.dto.service.UpdateCpnSDto;
import salute.oneshot.domain.coupon.service.CouponService;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {

    private final CouponService couponService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CpnBriefResponseDto>> createCoupon(
            @RequestBody CpnRequestDto requestDto
    ) {
        CreateCpnSDto serviceDto =
                CreateCpnSDto.of(
                        requestDto.getCouponName(),
                        requestDto.getDiscountValue(),
                        requestDto.getStartDate(),
                        requestDto.getStartTime(),
                        requestDto.getEndDate(),
                        requestDto.getEndTime());
        CpnBriefResponseDto responseDto =
                couponService.createCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_CPN_SUCCESS,
                        responseDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CpnDetailResponseDto>> updateCoupon(
            @PathVariable Long couponId,
            @RequestBody CpnRequestDto requestDto
    ) {
        UpdateCpnSDto serviceDto =
                UpdateCpnSDto.of(couponId, requestDto);
        CpnDetailResponseDto responseDto =
                couponService.updateCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_CPN_SUCCESS,
                        responseDto));
    }
}
