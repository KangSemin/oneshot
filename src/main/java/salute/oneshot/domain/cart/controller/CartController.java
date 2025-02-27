package salute.oneshot.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
import salute.oneshot.domain.cart.dto.service.UpdateItemQuantitySDto;
import salute.oneshot.domain.cart.service.CartService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<CartItemResponseDto>> addItem(
            @RequestBody AddCartItemRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        AddCartItemSDto sdto = AddCartItemSDto.of(userDetails.getId(), requestDto.getProductId(), requestDto.getQuantity());

        CartItemResponseDto responseDto = cartService.addCartItem(sdto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseConst.ADD_CART_ITEM_SUCCESS, responseDto)        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> findCart(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        CartResponseDto responseDto = cartService.findCart(userDetails.getId());

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.GET_CART_SUCCESS, responseDto)
        );
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<CartItemResponseDto>> updateItemQuantity(
            @PathVariable Long itemId,
            @RequestBody UpdateCartItemAmountRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UpdateItemQuantitySDto sdto = UpdateItemQuantitySDto.of(userDetails.getId(), itemId, requestDto.getQuantity());
        CartItemResponseDto responseDto = cartService.updateItemQuantity(sdto);

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.UPDATE_CART_ITEM_QUANTITY_SUCCESS, responseDto)
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cartService.removeItem(userDetails.getId(), itemId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(ApiResponseConst.REMOVE_CART_ITEM_SUCCESS));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> emptyCart(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        cartService.emptyCart(userDetails.getId());

        return ResponseEntity.ok(
                ApiResponse.success(ApiResponseConst.GET_PMNT_SUCCESS)
        );
    }
}
