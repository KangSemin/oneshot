package salute.oneshot.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailForUploadRequestDto;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.request.UpdateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.*;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;
import salute.oneshot.global.util.S3Uploader;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;
    private final S3Uploader s3Uploader;

//    @PostMapping
//    public ResponseEntity<ApiResponse<CocktailResponseDto>> createCocktail(
//        @RequestBody CreateCocktailRequestDto request,
//        @AuthenticationPrincipal CustomUserDetails userDetails) {
//
//        CreateCocktailSDto sDto = CreateCocktailSDto.of(userDetails.getId(),
//            userDetails.getUserRole(), request.getName(),
//            request.getDescription(), request.getRecipe(), request.getIngredientList());
//
//        cocktailService.createCocktail(sDto);
//
//        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_RCP_SUCCESS));
//    }

    // 이미지 업로드용 API
    // MultipartFile을 사용하는 방식은 Form 형태로 받기 위해서 @ModelAttribute 사용
    // TODO:
    @PostMapping
    public ResponseEntity<ApiResponse<CocktailResponseDto>> createCocktailForUpload(
        @ModelAttribute CreateCocktailForUploadRequestDto request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        // 예시코드
        // S3Uploader 빈 의존성 주입 필요
        MultipartFile imageFile = request.getImageFile();

        String imageFileName;
        if (imageFile != null) {
            try {
                imageFileName = s3Uploader.upload(imageFile);
            } catch (IOException e) {
                // IOException 예외처리
            }
        }
        // 파일 이름을 엔티티에 함께 저장
        // Url ex) https://oneshot-bucket2.s3.ap-northeast-2.amazonaws.com/(파일이름).jpg
        // 사진을 조회할 때 -> amazonS3.getUrl(bucketName, s3FileName).toString()


        CreateCocktailSDto sDto = CreateCocktailSDto.of(userDetails.getId(),
            userDetails.getUserRole(), request.getName(),
            request.getDescription(), request.getRecipe(), request.getIngredientList());

        cocktailService.createCocktail(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.ADD_RCP_SUCCESS));
    }


    @GetMapping("/{cocktailId}")
    public ResponseEntity<ApiResponse<CocktailResponseDto>> getCocktail(
        @PathVariable Long cocktailId) {

        CocktailResponseDto response = cocktailService.getCocktail(cocktailId);

        return ResponseEntity.ok(
            ApiResponse.success(ApiResponseConst.GET_CCKTL_SUCCESS, response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<CocktailResponseDto>>> searchWithIngredients(
        @RequestBody SearchCocktailByIngrsReqDto request,
        @RequestParam(required = false) RecipeType recipeType,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size) throws IOException{

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