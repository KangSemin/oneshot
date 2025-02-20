package salute.oneshot.domain.ingredient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.service.IngredientService;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ApiResponse<IngrResponseDto>> createIngredient(
        @Valid @RequestBody CreateIngrRequestDto request) {

        CreateIngrSDto sdto = CreateIngrSDto.of(request.getName(), request.getDescription(),
            IngredientCategory.valueOf(request.getCategory()), request.getAvb());

        IngrResponseDto responseDto = ingredientService.createIngredient(sdto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(ApiResponseConst.ADD_INGR_SUCCESS, responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<IngrResponseDto>>> getAllIngredients(
        @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page-1, size);
        Page<IngrResponseDto> responseDto = ingredientService.getAllIngredients(pageable);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.GET_INGR_SUCCESS, responseDto));
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<IngrResponseDto>> getIngredient(
        @PathVariable Long ingredientId) {

        IngrResponseDto responseDto = ingredientService.getIngredient(ingredientId);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.GET_INGR_SUCCESS, responseDto));
    }


    @PatchMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<IngrResponseDto>> updateIngredient(
        @PathVariable Long ingredientId,
        @Valid @RequestBody UpdateIngrRequestDto request) {

        UpdateIngrSDto sdto = UpdateIngrSDto.of(ingredientId, request.getName(),
            request.getDescription(),
            IngredientCategory.valueOf(request.getCategory()), request.getAvb());

        IngrResponseDto responseDto = ingredientService.updateIngredient(sdto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.UPDATE_INGR_SUCCESS, responseDto));
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<Void>> deleteIngredient(
        @PathVariable Long ingredientId) {

        ingredientService.deleteIngredient(ingredientId);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_INGR_SUCCESS));
    }
}
