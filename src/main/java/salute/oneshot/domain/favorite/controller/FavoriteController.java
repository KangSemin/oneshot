package salute.oneshot.domain.favorite.controller;

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
import salute.oneshot.domain.favorite.dto.response.FavoritePageResponseDto;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.response.GetFavoriteStatusDto;
import salute.oneshot.domain.favorite.dto.service.DeleteFavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.FavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.GetFavoritesSDto;
import salute.oneshot.domain.favorite.service.FavoriteService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/cocktails/{cocktailId}/favorites")
    public ResponseEntity<ApiResponse<FavoriteResponseDto>> createFavorite(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long cocktailId
    ) {
        FavoriteSDto serviceDto =
                FavoriteSDto.of(userDetails.getId(), cocktailId);
        FavoriteResponseDto responseDto =
                favoriteService.createFavorite(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_FVRT_SUCCESS, responseDto));
    }

    @GetMapping("/cocktails/{cocktailId}/favorites")
    public ResponseEntity<ApiResponse<GetFavoriteStatusDto>> checkFavorite(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long cocktailId
    ) {
        FavoriteSDto serviceDto =
                FavoriteSDto.of(userDetails.getId(), cocktailId);
        GetFavoriteStatusDto responseDto =
                favoriteService.checkFavorite(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_FVRT_STAT_SUCCESS, responseDto));
    }

    @GetMapping("/favorites")
    public ResponseEntity<ApiResponse<FavoritePageResponseDto>> getFavorites(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by("modifiedAt").descending()
        );

        GetFavoritesSDto serviceDto =
                GetFavoritesSDto.of(userDetails.getId(), pageable);

        FavoritePageResponseDto responseDto =
                favoriteService.getFavorites(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_FVRT_LIST_SUCCESS, responseDto));
    }

    @DeleteMapping("/favorites/{favoriteId}")
    public ResponseEntity<ApiResponse<FavoriteResponseDto>> deleteFavorite(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long favoriteId
    ) {
        DeleteFavoriteSDto serviceDto =
                DeleteFavoriteSDto.of(userDetails.getId(), favoriteId);
        FavoriteResponseDto responseDto =
                favoriteService.deleteFavorite(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_FVRT_LIST_SUCCESS, responseDto));
    }
}
