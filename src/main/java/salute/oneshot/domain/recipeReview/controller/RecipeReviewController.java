package salute.oneshot.domain.recipeReview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.recipeReview.dto.request.CreateRecipeReviewRequestDto;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.CreateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.service.RecipeReviewService;


@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeReviewController {

    private final RecipeReviewService recipeReviewService;

    @PostMapping("{recipeId}/reviews")
    public ResponseEntity<ApiResponse<RecipeReviewResponseDto>> createRecipeReview(
            @PathVariable("recipeId") Long recipeId, @Valid @RequestBody CreateRecipeReviewRequestDto requestDto) {

        CreateRecipeReviewSDto sDto = CreateRecipeReviewSDto.of(requestDto.getStar(), requestDto.getContent(), recipeId);

        RecipeReviewResponseDto responseDto = recipeReviewService.createRecipeReview(sDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseMessage.ADD_RCP_RVW_SUCCESS,responseDto));
    }


}
