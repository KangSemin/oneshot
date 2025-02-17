package salute.oneshot.domain.pantry.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.pantry.dto.response.PantryResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddIngrToPantrySDto;
import salute.oneshot.domain.pantry.dto.service.RemoveIngrFromPantrySDto;
import salute.oneshot.domain.pantry.service.PantryService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;

    @PostMapping("/ingredients/{ingredientId}")
    public ResponseEntity<ApiResponse<PantryResponseDto>> addIngrToPantry(
        @PathVariable Long ingredientId,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        AddIngrToPantrySDto sDto = AddIngrToPantrySDto.of(userDetails.getId(), ingredientId);
        PantryResponseDto response = pantryService.addIngredientToPantry(sDto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.ADD_PNTR_INGR_SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PantryResponseDto>>> getMyPantry(
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        List<PantryResponseDto> response = pantryService.getPantry(userDetails.getId());

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_PNTR_SUCCESS, response));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> clearPantryIngredients(
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        pantryService.clearPantryIngredients(userDetails.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(ApiResponse.success(ApiResponseConst.DELETE_PNTR_SUCCESS));
    }

    @DeleteMapping("/ingredients")
    public ResponseEntity<ApiResponse<Void>> removeIngredient(@RequestParam List<Long> ingredientIds,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        RemoveIngrFromPantrySDto sDto = RemoveIngrFromPantrySDto.of(userDetails.getId(),
            ingredientIds);

        pantryService.removeIngredientsFromPantry(sDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(ApiResponse.success(ApiResponseConst.DELETE_INGR_SUCCESS));
    }


}
