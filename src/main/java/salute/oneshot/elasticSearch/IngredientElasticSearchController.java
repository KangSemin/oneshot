package salute.oneshot.elasticSearch;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.service.IngredientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("elastic/ingr")
public class IngredientElasticSearchController {

    private final IngredientElasticService service;



    @PostMapping
    public ResponseEntity<ApiResponse<IngrDocResponseDto>> createIngr(@RequestBody CreateIngrRequestDto dto){

        IngrDocResponseDto responseDto = service.createIngr(dto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_INGR_SUCCESS, responseDto));
    }

}
