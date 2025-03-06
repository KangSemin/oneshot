package salute.oneshot.domain.ingredient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailForUploadRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.CreateCocktailSDto;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.SearchIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.service.IngredientService;
import salute.oneshot.global.security.entity.CustomUserDetails;
import salute.oneshot.global.util.S3Util;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final S3Util s3Util;

    @PostMapping
    public ResponseEntity<ApiResponse<IngrResponseDto>> createIngredient ( @Valid @ModelAttribute CreateIngrRequestDto request) throws IOException {


        CreateIngrSDto sdto = CreateIngrSDto.of(request.getName(), request.getDescription(),
            IngredientCategory.valueOf(request.getCategory()), request.getAvb(), request.getImageFile());

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

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<IngrResponseDto>>> getIngredientByCondition(@RequestParam(name = "keyword", required = false)String keyword,
                                                                                       @RequestParam(name = "category", required = false)String category,
                                                                                       @RequestParam(name = "size", defaultValue = "10") int size,
                                                                                       @RequestParam(name = "page", defaultValue = "1") int page)throws IOException{
        Pageable pageable = PageRequest.of(page - 1, size);
        SearchIngrSDto sDto = SearchIngrSDto.of(keyword, category, pageable);
        Page<IngrResponseDto> responseDtoPage = ingredientService.searchByCondition(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_INGR_LIST_SUCCESS, responseDtoPage));
    }


    @PatchMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<IngrResponseDto>> updateIngredient (
        @PathVariable Long ingredientId,
        @Valid @RequestBody UpdateIngrRequestDto request) throws IOException{

        UpdateIngrSDto sdto = UpdateIngrSDto.of(ingredientId, request.getName(),
            request.getDescription(),
            IngredientCategory.valueOf(request.getCategory()), request.getAvb());

        IngrResponseDto responseDto = ingredientService.updateIngredient(sdto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.UPDATE_INGR_SUCCESS, responseDto));
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<Void>> deleteIngredient(
        @PathVariable Long ingredientId) throws IOException{

        ingredientService.deleteIngredient(ingredientId);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_INGR_SUCCESS));
    }
}
