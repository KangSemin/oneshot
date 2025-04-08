package salute.oneshot.domain.cocktail.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;
    private final RedisTemplate<String, String> redisTemplate;
    private final S3Util s3Util;

    final String ABUSING_PREFIX = "abusing::";

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
    private ResponseEntity<ApiResponse<CocktailResponseDto>> getCocktail(@RequestHeader(value = "Referer", required = false) String referer,
                                                                         HttpServletRequest request,
                                                                         HttpServletResponse httpResponse,
                                                                         @PathVariable(name = "cocktailId") long cocktailId
    ) {

        ResponseEntity<ApiResponse<CocktailResponseDto>> response =  ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_CCKTL_SUCCESS,
                cocktailService.getCocktail(cocktailId)));

        if (!(referer.contains("/api/cocktails/popular" ) || referer.contains("/api/cocktails/condition") || referer.contains("/api/cocktails/search"))) {// 이 부분을 인기칵테일 or 검색 url이 아니면으로 변경해야함
            return response;
        }

        String ip = request.getHeader("X-Forwarded-For").split(",")[0];
        String userAgent = request.getHeader("User_Agent");

        log.info("값 :" + ip + userAgent);

        String cookieName = "abusing";

        Cookie cookie = CookieUtil.getOrCreateCookie(request, cookieName);

        List<String> values = redisTemplate.opsForList().range(ABUSING_PREFIX + cocktailId, 0, -1);
        boolean isExist = !values.isEmpty() && values.contains(cookie.getValue());

        if (!isExist) {
            cocktailService.increaseViewCountAndScore(cocktailId);
            redisTemplate.opsForList().rightPush(ABUSING_PREFIX + cocktailId, cookie.getValue());
        }

        CookieUtil.setCookieTime(cookie);
        httpResponse.addCookie(cookie);

        return response;
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

    @GetMapping("/condition")
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