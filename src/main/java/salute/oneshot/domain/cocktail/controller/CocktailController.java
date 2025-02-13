package salute.oneshot.domain.cocktail.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.request.UpdateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.CreateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.DeleteCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.UpdateCocktailSDto;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @PostMapping
    public ResponseEntity<ApiResponse<CocktailResponseDto>> createCocktail(
        @RequestBody CreateCocktailRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        CreateCocktailSDto sDto = CreateCocktailSDto.of(userId, request.getName(),
            request.getDescription(), request.getRecipe(), request.getIngredientList());

        cocktailService.createCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.ADD_RCP_SUCCESS));
    }

    @GetMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<CocktailResponseDto>> getCocktail(
        @PathVariable Long cocktailId) {

        CocktailResponseDto response = cocktailService.getCocktail(cocktailId);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseMessage.GET_CCKTL_SUCCESS, response));
    }

    @PatchMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<CocktailResponseDto>> updateCocktail(
        @PathVariable Long cocktailId,
        @RequestBody UpdateCocktailRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        UpdateCocktailSDto sDto = UpdateCocktailSDto.of(cocktailId, userDetails.getId(),
            request.getName(), request.getRecipe(),
            request.getDescription(), request.getIngredientList());

        CocktailResponseDto response = cocktailService.updateCocktail(sDto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseMessage.UPDATE_CCKTL_SUCCESS, response));
    }

    @DeleteMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<Void>> deleteCocktail(@PathVariable Long cocktailId,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        DeleteCocktailSDto sDto = DeleteCocktailSDto.of(userDetails.getId(), cocktailId);
        cocktailService.deleteCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.DELETE_CCKTL_SUCCESS));
    }
}