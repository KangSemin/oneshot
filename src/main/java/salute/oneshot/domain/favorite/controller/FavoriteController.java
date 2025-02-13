package salute.oneshot.domain.favorite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.service.CreateFavoriteSDto;
import salute.oneshot.domain.favorite.service.FavoriteService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/recipes/{recipeId}/favorites")
    public ResponseEntity<ApiResponse<FavoriteResponseDto>> createFavorite(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long recipeId
    ) {
        CreateFavoriteSDto serviceDto =
                CreateFavoriteSDto.of(customUserDetails.getId(), recipeId);
        FavoriteResponseDto responseDto =
                favoriteService.createFavorite(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(ApiResponseMessage.ADD_FVRT_SUCCESS, responseDto));
    }
}
