package salute.oneshot.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
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
//            @AuthenticationPrincipal Long userId
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;

        AddCartItemSDto sdto = AddCartItemSDto.of(userId, requestDto.getProductId(), requestDto.getAmount());
        CartItemResponseDto responseDto = cartService.addCartItem(sdto);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.ADD_CART_ITEM_SUCCESS, responseDto)
        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> findCart(
//            @AuthenticationPrincipal Long userId
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;

        CartResponseDto responseDto = cartService.findCart(userId);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.GET_CART_SUCCESS, responseDto)
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> emptyCart(
//            @AuthenticationPrincipal Long userId
    ) {
//        TODO: 시큐리티 설정 후 테스트용 데이터 지우기
        Long userId = 1L;

        CartResponseDto responseDto = cartService.emptyCart(userId);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseMessage.GET_CART_SUCCESS, responseDto)
        );
    }
}
