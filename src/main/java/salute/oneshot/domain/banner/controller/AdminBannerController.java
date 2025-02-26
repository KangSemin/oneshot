package salute.oneshot.domain.banner.controller;

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
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;

@RestController
@RequestMapping("/api/admin/banners")
@RequiredArgsConstructor
public class AdminBannerController {

    private final BannerService bannerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<BannerResponseDto>> createBanner(
            @RequestBody BannerRequestDto requestDto
    ) {
        BannerSDto serviceDto = BannerSDto.of(
                requestDto.getEventId(),
                requestDto.getImageUrl(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime());
        BannerResponseDto responseDto =
                bannerService.createBanner(serviceDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_BANNER_SUCCESS,
                        responseDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{bannerId}")
    public ResponseEntity<ApiResponse<BannerResponseDto>> updateBanner(
            @PathVariable Long bannerId,
            @RequestBody BannerRequestDto requestDto
    ) {
        UpdateBannerSDto serviceDto = UpdateBannerSDto.of(
                bannerId, requestDto.getEventId(),
                requestDto.getImageUrl(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime());
        BannerResponseDto responseDto =
                bannerService.updateBanner(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_BANNER_SUCCESS,
                        responseDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{bannerId}")
    public ResponseEntity<ApiResponse<Long>> deleteBanner(
            @PathVariable Long bannerId
    ) {
        Long deletedId =
                bannerService.deleteBanner(bannerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_BANNER_SUCCESS,
                        deletedId));
    }
}
