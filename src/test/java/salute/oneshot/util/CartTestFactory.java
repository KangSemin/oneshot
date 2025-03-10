package salute.oneshot.util;

import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.product.entity.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CartTestFactory {

    public static final Long CART_ITEM_ID = 1L;
    public static final int QUANTITY = 3;
    public static final int UPDATED_QUANTITY = 2;

    public static Cart createCart() {
        return Cart.from(UserTestFactory.createUser());
    }

    public static CartItem createCartItem() {
        Cart cart = createCart();
        Product product = ProductTestFactory.createProduct();

        return CartItem.of(cart, product, QUANTITY);
    }

    public static CartItemResponseDto createCartItemResponseDto() {
        CartItem cartItem = createCartItem();
        return CartItemResponseDto.from(cartItem);
    }

    public static AddCartItemRequestDto createAddCartItemRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<AddCartItemRequestDto> constructor =
                AddCartItemRequestDto.class.getDeclaredConstructor(Long.class, Integer.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ProductTestFactory.PRODUCT_ID, QUANTITY);
    }

    public static UpdateCartItemAmountRequestDto createUpdateCartItemAmountRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateCartItemAmountRequestDto> constructor =
                UpdateCartItemAmountRequestDto.class.getDeclaredConstructor(Integer.class);
        constructor.setAccessible(true);

        return constructor.newInstance(UPDATED_QUANTITY);
    }

    public static CartResponseDto createCartResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CartResponseDto> constructor =
                CartResponseDto.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        List<CartItemResponseDto> itemList = new ArrayList<>();
        itemList.add(createCartItemResponseDto());

        return constructor.newInstance(itemList);
    }
}
