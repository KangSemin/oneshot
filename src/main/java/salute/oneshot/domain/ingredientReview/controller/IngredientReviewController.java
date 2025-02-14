package salute.oneshot.domain.ingredientReview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredientReview.dto.request.CreateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.service.CreateIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.DeleteIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetMyIngredientReviewSDto;
import salute.oneshot.domain.ingredientReview.service.IngredientReviewService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientReviewController {

    private final IngredientReviewService ingredientReviewService;

    @PostMapping("/{ingredientId}/reviews")
    public ResponseEntity<ApiResponse<IngrReviewResponseDto>> createIngredientReview (
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("ingredientId") Long ingredientId,
            @Valid @RequestBody CreateIngrReviewRequestDto requestDto) {

        Long userId = userDetails.getId();

        CreateIngrReviewSDto sDto = CreateIngrReviewSDto.of
                (userId, ingredientId, requestDto.getStar(), requestDto.getContent());

        IngrReviewResponseDto responseDto = ingredientReviewService.createIngredientReview(sDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiResponseConst.ADD_INGR_RVW_SUCCESS,responseDto));
    }

    @GetMapping("/reviews/me")
    public ResponseEntity<ApiResponse<Page<IngrReviewResponseDto>>> getMyIngredientReview(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Long userId = userDetails.getId();

        Pageable pageable = PageRequest.of(page - 1, size);

        GetMyIngredientReviewSDto sDto = GetMyIngredientReviewSDto.of(userId, pageable);

        Page<IngrReviewResponseDto> responseDtoPage = ingredientReviewService.getMyIngredientReview(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_INGR_RVW_LIST_SUCCESS,responseDtoPage));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<IngrReviewResponseDto>> deleteIngredientReview(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("reviewId") Long reviewId) {

        Long userId = userDetails.getId();

        DeleteIngrReviewSDto sDto = DeleteIngrReviewSDto.of(reviewId, userId);

        ingredientReviewService.deleteIngredientReview(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_INGR_RVW_SUCCESS));
    }
}
