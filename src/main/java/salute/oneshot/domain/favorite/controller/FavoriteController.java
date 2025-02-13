package salute.oneshot.domain.favorite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.service.FavoriteSDto;
import salute.oneshot.domain.favorite.service.FavoriteService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/cocktails/{cocktailId}/favorites")
    public ResponseEntity<ApiResponse<FavoriteResponseDto>> createFavorite(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long cocktailId
    ) {
        FavoriteSDto serviceDto =
                FavoriteSDto.of(customUserDetails.getId(), cocktailId);
        FavoriteResponseDto responseDto =
                favoriteService.createFavorite(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseMessage.ADD_FVRT_SUCCESS, responseDto));
    }
}
