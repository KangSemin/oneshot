package salute.oneshot.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.product.dto.request.CreateProductRequestDto;
import salute.oneshot.domain.product.dto.request.UpdateProductRequestDto;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.dto.service.CreateProductSDto;
import salute.oneshot.domain.product.dto.service.GetAllProductSDto;
import salute.oneshot.domain.product.dto.service.UpdateProductRequestSDto;
import salute.oneshot.domain.product.dto.service.DeleteProductSDto;
import salute.oneshot.domain.product.service.ProductService;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody CreateProductRequestDto requestDto) {

        Long userId = userDetails.getId();

        CreateProductSDto sDto = CreateProductSDto.of(userId, requestDto.getName(), requestDto.getDescription(),
                requestDto.getCategory(), requestDto.getPrice(), requestDto.getStockQuantity(), requestDto.getStatus()
        );

        ProductResponseDto responseDto = productService.createProduct(sDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiResponseConst.ADD_PRDT_SUCCESS, responseDto));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateProductRequestDto requestDto,
            @PathVariable("productId") Long productId) {

        Long userId = userDetails.getId();

        UpdateProductRequestSDto sDto = UpdateProductRequestSDto
                .of(userId, requestDto.getName(), requestDto.getDescription(), requestDto.getCategory(),
                        requestDto.getPrice(), requestDto.getStockQuantity(), requestDto.getStatus(), productId);

        ProductResponseDto responseDto = productService.updateProduct(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.UPDATE_PRDT_SUCCESS, responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponseDto>>> getAllProduct(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        GetAllProductSDto sDto = GetAllProductSDto.of(category, pageable);

        Page<ProductResponseDto> responseDtos = productService.getAllProduct(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_PRDT_SUCCESS,responseDtos));
    }

    @GetMapping("{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(
            @PathVariable("productId") Long productId) {

        ProductResponseDto responseDto = productService.getProduct(productId);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_PRDT_SUCCESS,responseDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("productId") Long productId) {

        Long userId = userDetails.getId();

        DeleteProductSDto sDto = DeleteProductSDto.of(userId, productId);

        productService.deleteProduct(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_PRDT_SUCCESS));
    }
}
