package salute.oneshot.domain.recipeReview.controller;

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
import salute.oneshot.domain.recipeReview.dto.request.CreateRecipeReviewRequestDto;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.CreateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.dto.service.GetAllRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.service.RecipeReviewService;
import salute.oneshot.global.security.entity.CustomUserDetails;


@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeReviewController {

    private final RecipeReviewService recipeReviewService;

    @PostMapping("/{cocktailId}/reviews")
    public ResponseEntity<ApiResponse<RecipeReviewResponseDto>> createRecipeReview(
            @PathVariable("cocktailId") Long cocktailId, @Valid @RequestBody CreateRecipeReviewRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        CreateRecipeReviewSDto sDto = CreateRecipeReviewSDto.of(requestDto.getStar(), requestDto.getContent(), userId, cocktailId);

        RecipeReviewResponseDto responseDto = recipeReviewService.createRecipeReview(sDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseConst.ADD_RCP_RVW_SUCCESS,responseDto));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse<RecipeReviewResponseDto>> getRecipeReview(
            @PathVariable ("reviewId") Long reviewId) {

        RecipeReviewResponseDto responseDto = recipeReviewService.getRecipeReview(reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(ApiResponseConst.GET_RCP_RVW_SUCCESS,responseDto));
    }


    @GetMapping("/{cocktailId}/reviews")
    public ResponseEntity<ApiResponse<Page<RecipeReviewResponseDto>>> getAllRecipeReview (
            @PathVariable ("cocktailId") Long cocktailId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        GetAllRecipeReviewSDto sDto = GetAllRecipeReviewSDto.of(pageable, cocktailId);

        Page<RecipeReviewResponseDto> responseDtos = recipeReviewService.getAllRecipeReview(sDto);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(ApiResponseConst.GET_RCP_RVW_SUCCESS,responseDtos));
    }

}
