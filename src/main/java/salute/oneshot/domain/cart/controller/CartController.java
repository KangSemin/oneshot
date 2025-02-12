package salute.oneshot.domain.cart.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
import salute.oneshot.domain.cart.dto.service.UpdateItemQuantitySDto;
import salute.oneshot.domain.cart.service.CartService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<CartItemResponseDto>> addItem(
            @RequestBody AddCartItemRequestDto requestDto
//            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;
        AddCartItemSDto sdto = AddCartItemSDto.of(userId, requestDto.getProductId(), requestDto.getQuantity());

        CartItemResponseDto responseDto = cartService.addCartItem(sdto);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.ADD_CART_ITEM_SUCCESS, responseDto)
        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> findCart(
//            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;

        CartResponseDto responseDto = cartService.findCart(userId);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.GET_CART_SUCCESS, responseDto)
        );
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<CartItemResponseDto>> updateItemQuantity(
            @PathVariable Long itemId,
            @RequestBody UpdateCartItemAmountRequestDto requestDto
//            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;
        UpdateItemQuantitySDto sdto = UpdateItemQuantitySDto.of(userId, itemId, requestDto) ;
        CartItemResponseDto responseDto = cartService.updateItemQuantity(sdto);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.UPDATE_CART_ITEM_QUANTITY_SUCCESS, responseDto)
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(
            @PathVariable Long itemId
//            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;
        cartService.removeItem(userId, itemId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(ApiResponseMessage.REMOVE_CART_ITEM_SUCCESS));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> emptyCart(
//            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;

        cartService.emptyCart(userId);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.EMPTY_CART_SUCCESS)
        );
    }
}
