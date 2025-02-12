package salute.oneshot.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
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

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartItemResponseDto addCartItem(AddCartItemSDto sdto) {
        Cart foundCart = cartRepository.findByUserId(sdto.getUserId()).orElseGet(() -> {
            User foundUser = userRepository.findById(sdto.getUserId()).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
            Cart newCart = Cart.from(foundUser);
            return cartRepository.save(newCart);
        });

        Product foundProduct = productRepository.findById(sdto.getProductId()).orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        CartItem newItem = CartItem.of(foundCart, foundProduct, sdto.getAmount());
        CartItem savedItem = cartItemRepository.save(newItem);

        return CartItemResponseDto.from(savedItem);
    }

    // 1건의 조회밖에 이루어지지 않기 때문에 트랜잭션을 사용하지 않음
    public CartResponseDto findCart(Long userId) {
        Optional<Cart> foundOptionalCart = cartRepository.findByUserId(userId);
        return foundOptionalCart.map(CartResponseDto::from).orElseGet(() -> CartResponseDto.empty(userId));
    }

    @Transactional
    public CartResponseDto emptyCart(Long userId) {
        cartRepository.findByUserId(userId).ifPresent(cart -> cart.getCartItemList().clear());
        return CartResponseDto.empty(userId);
    }
}
