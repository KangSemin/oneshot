package salute.oneshot.domain.ingredientReview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredientReview.dto.request.CreateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.service.CreateIngrReviewSDto;
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


}
