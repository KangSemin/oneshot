package salute.oneshot.domain.banner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.domain.banner.dto.response.BannerResponseDto;
import salute.oneshot.domain.banner.dto.service.BannerSDto;
import salute.oneshot.domain.banner.dto.service.UpdateBannerSDto;
import salute.oneshot.domain.banner.service.BannerService;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.exception.InvalidException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/banners")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminBannerController {

    private final BannerService bannerService;

    @PostMapping
    public ResponseEntity<ApiResponse<BannerResponseDto>> createBanner(
            @Valid @RequestBody BannerRequestDto requestDto
    ) {
        BannerSDto serviceDto = BannerSDto.of(
                requestDto.getEventId(),
                requestDto.getImageUrl(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime());
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());

        BannerResponseDto responseDto =
                bannerService.createBanner(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_BANNER_SUCCESS,
                        responseDto));
    }

    @PatchMapping("/{bannerId}")
    public ResponseEntity<ApiResponse<BannerResponseDto>> updateBanner(
            @PathVariable Long bannerId,
            @Valid @RequestBody BannerRequestDto requestDto
    ) {
        UpdateBannerSDto serviceDto = UpdateBannerSDto.of(
                bannerId, requestDto.getEventId(),
                requestDto.getImageUrl(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime());
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());

        BannerResponseDto responseDto =
                bannerService.updateBanner(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_BANNER_SUCCESS,
                        responseDto));
    }

    @DeleteMapping("/{bannerId}")
    public ResponseEntity<ApiResponse<Long>> deleteBanner(
            @PathVariable Long bannerId
    ) {
        bannerService.deleteBanner(bannerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_BANNER_SUCCESS,
                        bannerId));
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
