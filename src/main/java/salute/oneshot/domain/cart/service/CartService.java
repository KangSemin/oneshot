package salute.oneshot.domain.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
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
}
