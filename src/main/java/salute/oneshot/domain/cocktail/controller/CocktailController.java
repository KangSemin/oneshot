package salute.oneshot.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CocktailResponseDto>>> getAllCocktails(@RequestParam(name = "page") int page,
                                                                                  @RequestParam(name = "size") int size

    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CocktailResponseDto> responsePage = cocktailService.getAllCocktails(pageable);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_CCKTL_LIST_SUCCESS, responsePage));
    }
}