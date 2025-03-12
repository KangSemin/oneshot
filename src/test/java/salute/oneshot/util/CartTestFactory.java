package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CartTestFactory {

    public static final Long CART_ITEM_ID = 1L;
    public static final Long CART_ID = 1L;
    public static final int QUANTITY = 3;
    public static final int UPDATED_QUANTITY = 2;

    public static Cart createCart() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        CartItem cartItem = createCartItem();

        Constructor<Cart> constructor = Cart.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Cart cart = constructor.newInstance();
        ReflectionTestUtils.setField(cart, "id", CART_ID);
        ReflectionTestUtils.setField(cart, "user", UserTestFactory.createUser());
        ReflectionTestUtils.setField(cart, "itemList", List.of(cartItem));
        ReflectionTestUtils.setField(cart, "isOrdered", false);

        ReflectionTestUtils.setField(cartItem, "cart", cart);
        return cart;
    }

    public static CartItem createCartItem() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CartItem> constructor = CartItem.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        CartItem cartItem = constructor.newInstance();
        ReflectionTestUtils.setField(cartItem, "id", CART_ITEM_ID);
//        ReflectionTestUtils.setField(cartItem, "cart", null);
        ReflectionTestUtils.setField(cartItem, "product", ProductTestFactory.createProduct());
        ReflectionTestUtils.setField(cartItem, "quantity", QUANTITY);

        return cartItem;
    }

    public static CartItemResponseDto createCartItemResponseDto() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor<CartItemResponseDto> constructor =
                CartItemResponseDto.class.getDeclaredConstructor(Long.class, ProductResponseDto.class, Integer.class);
        constructor.setAccessible(true);

        return constructor.newInstance(CART_ITEM_ID, ProductTestFactory.createProductResponseDto(), QUANTITY);
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

        List<CartItemResponseDto> itemList = List.of(createCartItemResponseDto());

        return constructor.newInstance(itemList);
    }
}
