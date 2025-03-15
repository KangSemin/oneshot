package salute.oneshot.domain.coupon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.domain.coupon.dto.request.UserCpnRequestDto;
import salute.oneshot.domain.coupon.dto.response.CpnBriefResponseDto;
import salute.oneshot.domain.coupon.dto.response.CpnDetailResponseDto;
import salute.oneshot.domain.coupon.dto.response.UserCpnBriefResponseDto;
import salute.oneshot.domain.coupon.dto.service.CreateCpnSDto;
import salute.oneshot.domain.coupon.dto.service.CreateUserCpnSDto;
import salute.oneshot.domain.coupon.dto.service.UpdateCpnSDto;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.global.exception.InvalidException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminCouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<ApiResponse<CpnBriefResponseDto>> createCoupon(
            @Valid @RequestBody CpnRequestDto requestDto
    ) {
        CreateCpnSDto serviceDto =
                CreateCpnSDto.of(
                        requestDto.getCouponName(),
                        requestDto.getDiscountValue(),
                        requestDto.getStartDate(),
                        requestDto.getStartTime(),
                        requestDto.getEndDate(),
                        requestDto.getEndTime());
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());

        CpnBriefResponseDto responseDto =
                couponService.createCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_CPN_SUCCESS,
                        responseDto));
    }

    @PatchMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CpnDetailResponseDto>> updateCoupon(
            @PathVariable Long couponId,
            @Valid @RequestBody CpnRequestDto requestDto
    ) {
        UpdateCpnSDto serviceDto =
                UpdateCpnSDto.of(couponId, requestDto);
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());

        CpnDetailResponseDto responseDto =
                couponService.updateCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_CPN_SUCCESS,
                        responseDto));
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<Long>> deleteCoupon(
            @PathVariable Long couponId
    ) {
        Long deletedId =
                couponService.deleteCoupon(couponId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_CPN_SUCCESS,
                        deletedId));
    }

    @PostMapping("/{couponId}/users")
    public ResponseEntity<ApiResponse<UserCpnBriefResponseDto>> grantUserCoupon(
            @PathVariable Long couponId,
            @Valid @RequestBody UserCpnRequestDto requestDto
    ) {
        CreateUserCpnSDto serviceDto =
                CreateUserCpnSDto.of(couponId, requestDto.getUserId());
        UserCpnBriefResponseDto responseDto =
                couponService.grantUserCoupon(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_USER_CPN_SUCCESS,
                        responseDto));
    }

    private void validateEventDate(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        if (endTime.isBefore(LocalDateTime.now())) {
            throw new InvalidException(ErrorCode.EXPIRED_DATE);
        }

        if (startTime.isAfter(endTime)) {
            throw new InvalidException(ErrorCode.INVALID_DATE);
        }
    }
}