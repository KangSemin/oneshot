package salute.oneshot.util;

import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;
import salute.oneshot.domain.user.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CartTestFactory {

    public static final Long USER_ID = 1L;

    public static Cart createCart(User user) {
        return Cart.from(user);
    }

    public static Product createProduct(User user) {
        return Product.of("보드카", "독한 술입니다.", 120000, 50, ProductCategory.ALCOHOL, ProductStatus.SALE, user);
    }

    public static CartItem createCartItem(User user) {
        Cart cart = createCart(user);
        Product product = createProduct(user);

        return CartItem.of(cart, product, 3);
    }

    public static CartItemResponseDto createCartItemResponseDto(User user) {
        CartItem cartItem = createCartItem(user);
        return CartItemResponseDto.from(cartItem);
    }

    public static AddCartItemRequestDto createAddCartItemRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<AddCartItemRequestDto> constructor =
                AddCartItemRequestDto.class.getDeclaredConstructor(Long.class, Integer.class);
        constructor.setAccessible(true);

        return constructor.newInstance(1L, 50);
    }

    public static UpdateCartItemAmountRequestDto createUpdateCartItemAmountRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateCartItemAmountRequestDto> constructor =
                UpdateCartItemAmountRequestDto.class.getDeclaredConstructor(Integer.class);
        constructor.setAccessible(true);

        return constructor.newInstance(2);
    }

    public static CartResponseDto createCartResponseDto(User user) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CartResponseDto> constructor =
                CartResponseDto.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        List<CartItemResponseDto> itemList = new ArrayList<>();
        itemList.add(createCartItemResponseDto(user));

        return constructor.newInstance(itemList);
    }
}
