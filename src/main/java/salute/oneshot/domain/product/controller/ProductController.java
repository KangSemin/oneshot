package salute.oneshot.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.product.dto.request.CreateProductRequestDto;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.dto.service.CreateProductSDto;
import salute.oneshot.domain.product.service.ProductService;
import salute.oneshot.global.security.entity.CustomUserDetails;

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

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiResponseConst.ADD_PRDT_SUCCESS,responseDto));
    }

}
