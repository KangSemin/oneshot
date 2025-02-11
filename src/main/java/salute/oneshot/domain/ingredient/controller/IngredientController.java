package salute.oneshot.domain.ingredient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.service.IngredientService;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ApiResponse<IngrResponseDto>> createIngredient(
        @Valid @RequestBody CreateIngrRequestDto request) {

        IngrResponseDto responseDto = ingredientService.createIngredient(
            CreateIngrSDto.of(request.getName(), request.getDescription(),
                request.getCategory(), request.getAvb()));

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(ApiResponseMessage.ADD_INGR_SUCCESS, responseDto));
    }

    @PatchMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<IngrResponseDto>> updateIngredient(
        @PathVariable Long ingredientId,
        @Valid @RequestBody UpdateIngrRequestDto request) {

        IngrResponseDto responseDto = ingredientService.updateIngredient(
            UpdateIngrSDto.of(ingredientId, request.getName(), request.getDescription(),
                request.getCategory(), request.getAvb()));

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseMessage.UPDATE_INGR_SUCCESS, responseDto));
    }
}
