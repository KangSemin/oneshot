package salute.oneshot.domain.banner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.banner.dto.response.BannerPageResponseDto;
import salute.oneshot.domain.banner.dto.response.BannerResponseDto;
import salute.oneshot.domain.banner.dto.service.GetBannersSDto;
import salute.oneshot.domain.banner.service.BannerService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<ApiResponse<BannerPageResponseDto>> getBanners(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by("endTime").ascending()
        );

        GetBannersSDto serviceDto =
                GetBannersSDto.of(startDate, endDate, pageable);

        BannerPageResponseDto responseDto =
                bannerService.getBanners(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_BANNER_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{bannerId}")
    public ResponseEntity<ApiResponse<BannerResponseDto>> getBanner(
            @PathVariable Long bannerId
    ) {
        BannerResponseDto responseDto =
                bannerService.getBanner(bannerId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_BANNER_SUCCESS,
                        responseDto));
    }
}
