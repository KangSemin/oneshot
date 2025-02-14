package salute.oneshot.domain.pantry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.pantry.dto.request.AddPantryIngrRequestDto;
import salute.oneshot.domain.pantry.dto.response.PantryIngrResponseDto;
import salute.oneshot.domain.pantry.dto.response.PantryResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddPantryIngredientSDto;
import salute.oneshot.domain.pantry.entity.Pantry;
import salute.oneshot.domain.pantry.service.PantryService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;

    @PostMapping
    public ResponseEntity<ApiResponse<PantryIngrResponseDto>> addIngrToPantry(@RequestBody AddPantryIngrRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        AddPantryIngredientSDto sDto = AddPantryIngredientSDto.of(userDetails.getId(), request.getIngredientId());
        PantryIngrResponseDto response = pantryService.addIngredientToPantry(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_PNTR_INGR_SUCCESS,response));
    }
}
