package salute.oneshot.domain.cocktail.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.request.UpdateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.*;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.model.CustomUserDetails;
import salute.oneshot.global.util.CookieUtil;
import salute.oneshot.global.util.S3Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;
    private final S3Util s3Util;

    @PostMapping
    public ResponseEntity<ApiResponse<CocktailResponseDto>> createCocktail(
            @RequestPart MultipartFile imageFile,
            @RequestBody CreateCocktailRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        CreateCocktailSDto sDto = CreateCocktailSDto.of(userDetails.getId(),
                userDetails.getUserRole(), request.getName(),
                request.getDescription(), request.getRecipe(), request.getIngredientList(), imageFile);

        cocktailService.createCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_RCP_SUCCESS));
    }



    @GetMapping("/{cocktailId}")
    private ResponseEntity<ApiResponse<CocktailResponseDto>> getCocktail(HttpServletRequest request,
                                                                                  HttpServletResponse httpResponse,
                                                                                  @PathVariable(name = "cocktailId") Long cocktailId
    ){

        String value = "[" + cocktailId + "]";

        Cookie cookie = CookieUtil.getOrCreateCookie(request, "viewCount");

        if(!CookieUtil.isExistValue(cookie, value)){
            cookie.setValue(cookie.getValue() + value);
            cocktailService.increaseViewCountAndScore(cocktailId);
        }

        long todayEndTime = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/");
        cookie.setMaxAge((int) (todayEndTime - currentTime));

        httpResponse.addCookie(cookie);

        CocktailResponseDto responseDto = cocktailService.getCocktail(cocktailId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_CCKTL_SUCCESS, responseDto));
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<CocktailResponseDto>>> searchWithIngredients(
        @RequestParam(required = true) List<Long> ingredientIds,
        @RequestParam(required = false) RecipeType recipeType,
        @RequestParam(defaultValue = "false") Boolean isCraftable,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size) throws IOException {

        SearchCocktailSDto sDto = SearchCocktailSDto.of(ingredientIds, isCraftable,
            recipeType, page, size);

        Page<CocktailResponseDto> response = cocktailService.getCocktailsByIngr(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_CCKTL_SUCCESS, response));
    }

    @PatchMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<CocktailResponseDto>> updateCocktail(
        @PathVariable Long cocktailId,
        @RequestBody UpdateCocktailRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        UpdateCocktailSDto sDto = UpdateCocktailSDto.of(cocktailId, userDetails.getId(),
            request.getName(), request.getRecipe(),
            request.getDescription(), request.getIngredientList());

        CocktailResponseDto response = cocktailService.updateCocktail(sDto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.UPDATE_CCKTL_SUCCESS, response));
    }

    @DeleteMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<Void>> deleteCocktail(@PathVariable Long cocktailId,
        @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {

        DeleteCocktailSDto sDto = DeleteCocktailSDto.of(userDetails.getId(),
            userDetails.getUserRole(), cocktailId);
        cocktailService.deleteCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_CCKTL_SUCCESS));
    }

    @GetMapping//조건별 검색
    public ResponseEntity<ApiResponse<Page<CocktailResponseDto>>> getCocktailsByCondition(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "recipeType", required = false) String recipeType
    ) throws IOException {
        Pageable pageable = PageRequest.of(page - 1, size);

        findCocktailSDto sDto = findCocktailSDto.of(pageable, keyword, recipeType);

        Page<CocktailResponseDto> responsePage = cocktailService.getIngrByCondition(sDto);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.GET_CCKTL_LIST_SUCCESS, responsePage));

    }


    @GetMapping("/popular")//인기 칵테일 조회
    public ResponseEntity<ApiResponse<List<CocktailResponseDto>>> getPopularCocktails() {

        List<CocktailResponseDto> dtoResponseList = cocktailService.getPopularCocktails();

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.GET_CCKTL_LIST_SUCCESS, dtoResponseList));
    }
}