package salute.oneshot.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
import salute.oneshot.domain.cart.dto.service.UpdateItemQuantitySDto;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.cart.repository.CartItemRepository;
import salute.oneshot.domain.cart.repository.CartRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.repository.ProductRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartItemResponseDto addCartItem(AddCartItemSDto sdto) {
        Cart foundCart = cartRepository.findByUserIdAndIsOrderedFalse(sdto.getUserId()).orElseGet(() -> {
            if (!userRepository.existsById(sdto.getUserId())) {
                throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
            }
            User userRef = userRepository.getReferenceById(sdto.getUserId());
            Cart newCart = Cart.from(userRef);
            cartRepository.save(newCart);
            return newCart;
        });

        if (!productRepository.existsById(sdto.getProductId())) {
            throw new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Product productRef = productRepository.getReferenceById(sdto.getProductId());
        CartItem newItem = CartItem.of(foundCart, productRef, sdto.getQuantity());
        cartItemRepository.save(newItem);

        return CartItemResponseDto.from(newItem);
    }

    // 1건의 조회만 이루어지기 때문에 트랜잭션을 사용하지 않음
    public CartResponseDto findCart(Long userId) {
        Optional<Cart> foundOptionalCart = cartRepository.findByUserIdAndIsOrderedFalse(userId);
        return foundOptionalCart.map(CartResponseDto::from).orElseGet(() -> CartResponseDto.empty(userId));
    }

    @Transactional
    public CartItemResponseDto updateItemQuantity(UpdateItemQuantitySDto sdto) {
        CartItem item = cartItemRepository.findById(sdto.getItemId()).orElseThrow(() -> new NotFoundException(ErrorCode.CART_ITEM_NOT_FOUND));

        isCartItemOrdered(item);
        isCartItemOwnedByUser(sdto.getUserId(), item);

        item.updateQuantity(sdto);

        return CartItemResponseDto.from(item);
    }

    @Transactional
    public void removeItem(Long userId, Long itemId) {
        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() -> new NotFoundException(ErrorCode.CART_ITEM_NOT_FOUND));

        isCartItemOrdered(item);
        isCartItemOwnedByUser(userId, item);

        cartItemRepository.deleteByIdAndCartUserId(itemId, userId);
    }

    @Transactional
    public void emptyCart(Long userId) {
        cartRepository.findByUserIdAndIsOrderedFalse(userId).ifPresent(cart -> cart.getItemList().clear());
    }

    private static void isCartItemOrdered(CartItem item) {
        if (item.getCart().getIsOrdered().equals(Boolean.TRUE)) {
            throw new NotFoundException(ErrorCode.CART_ITEM_NOT_FOUND);
        }
    }

    private static void isCartItemOwnedByUser(Long userId, CartItem item) {
        if (!item.getCart().getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorCode.CART_ITEM_UNAUTHORIZED);
        }
    }
}
