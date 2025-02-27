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
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private static final int MAX_CART_ITEM_LIST_SIZE = 15;

    @Transactional
    public CartItemResponseDto addCartItem(AddCartItemSDto sdto) {

        Product foundProduct = productRepository.findById(sdto.getProductId()).orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        if (foundProduct.getStockQuantity() < sdto.getQuantity()) {
            throw new ConflictException(ErrorCode.CART_ITEM_MORE_THAN_STOCK_QUANTITY);
        }

        Cart foundCart = cartRepository.findByUserIdAndIsOrderedFalse(sdto.getUserId()).orElseGet(() -> {
            User userRef = userRepository.getReferenceById(sdto.getUserId());
            Cart newCart = Cart.from(userRef);
            return cartRepository.save(newCart);
        });

        if (foundCart.getItemList().size() >= MAX_CART_ITEM_LIST_SIZE) {
            throw new InvalidException(ErrorCode.FULL_CART);
        }

        CartItem newItem = CartItem.of(foundCart, foundProduct, sdto.getQuantity());
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
        CartItem item = getItemByIdIfItemIsNotOrdered(sdto.getItemId());
        isCartItemOwnedByUser(sdto.getUserId(), item);

        item.updateQuantity(sdto);

        return CartItemResponseDto.from(item);
    }

    @Transactional
    public void removeItem(Long userId, Long itemId) {
        CartItem item = getItemByIdIfItemIsNotOrdered(itemId);
        isCartItemOwnedByUser(userId, item);
        cartItemRepository.deleteByIdAndCartUserId(itemId, userId);
    }

    @Transactional
    public void emptyCart(Long userId) {
        cartRepository.findByUserIdAndIsOrderedFalse(userId).ifPresent(cart -> cart.getItemList().clear());
    }

    private CartItem getItemByIdIfItemIsNotOrdered(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId).orElseThrow(() -> new NotFoundException(ErrorCode.CART_ITEM_NOT_FOUND));
        isCartItemOrdered(item);
        return item;
    }

    private static void isCartItemOrdered(CartItem item) {
        if (item.getCart().getIsOrdered().equals(Boolean.TRUE)) {
            throw new NotFoundException(ErrorCode.CART_ITEM_NOT_FOUND);
        }
    }

    private static void isCartItemOwnedByUser(Long userId, CartItem item) {
        if (!item.getCart().getUser().getId().equals(userId)) {
            throw new ForbiddenException(ErrorCode.CART_ITEM_FORBIDDEN);
        }
    }
}
