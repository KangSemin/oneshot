package salute.oneshot.domain.cocktail.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.request.UpdateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.*;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @PostMapping
    public ResponseEntity<ApiResponse<CocktailResponseDto>> createCocktail(
            @RequestBody CreateCocktailRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        CreateCocktailSDto sDto = CreateCocktailSDto.of(userDetails.getId(),
                userDetails.getUserRole(), request.getName(),
                request.getDescription(), request.getRecipe(), request.getIngredientList());

        cocktailService.createCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_RCP_SUCCESS));
    }


    @GetMapping("/{cocktailId}")
    private ResponseEntity<ApiResponse<CocktailResponseDto>> getCocktail(HttpServletRequest request,
                                                                                  HttpServletResponse httpResponse,
                                                                                  @PathVariable(name = "cocktailId") Long cocktailId
    ){

        Cookie[] cookies = request.getCookies();

        Cookie cookie = null;

        boolean isCookieExist = false;

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("viewCount")).findAny();

        if(optionalCookie.isEmpty()){

            cookie = new Cookie("viewCount", "");

        }else {

            cookie = optionalCookie.get();
            isCookieExist = cookie.getValue().contains("[" + cocktailId + "]");

        }

        if (!isCookieExist) {
            cookie.setValue(cookie.getValue() + "[" + cocktailId + "]");
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
            @RequestBody SearchCocktailByIngrsReqDto request,
            @RequestParam(required = false) RecipeType recipeType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) throws IOException {

        SearchCocktailSDto sDto = SearchCocktailSDto.of(request.getIngredientIds(), recipeType, page, size);

        Page<CocktailResponseDto> response = cocktailService.findCocktailsByIngr(sDto);

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
        @RequestParam(name = "recipeType", required = false) String  recipeType
    )throws IOException {
        Pageable pageable = PageRequest.of(page - 1, size);

        findCocktailSDto sDto = findCocktailSDto.of(pageable, keyword, recipeType);

        Page<CocktailResponseDto> responsePage = cocktailService.searchByCondition(sDto);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.GET_CCKTL_LIST_SUCCESS, responsePage));

    }


    @GetMapping("/popular")//인기 칵테일 조회
    public ResponseEntity<ApiResponse<Page<CocktailResponseDto>>> getPopularCocktails(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1 , size);


        List<CocktailResponseDto> dtoResponse = cocktailService.getPopularCocktails();
        Page<CocktailResponseDto> responsePage = new PageImpl<>(dtoResponse, pageable, dtoResponse.size());

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.GET_CCKTL_LIST_SUCCESS, responsePage));
    }
}