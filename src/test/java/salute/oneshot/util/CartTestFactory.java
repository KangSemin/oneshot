package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CartTestFactory {

    public static final Long CART_ITEM_ID = 1L;
    public static final int QUANTITY = 3;
    public static final int UPDATED_QUANTITY = 2;

    // Cart 필드값 user 제외하면 없는 상태
    public static Cart createCart() {
        return Cart.from(UserTestFactory.createUser());
    }

    public static CartItem createCartItem() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CartItem> constructor = CartItem.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CartItem cartItem = constructor.newInstance();

        ReflectionTestUtils.setField(cartItem, "id", CART_ITEM_ID);
        ReflectionTestUtils.setField(cartItem, "cart", createCart());
        ReflectionTestUtils.setField(cartItem, "product", ProductTestFactory.createProduct());
        ReflectionTestUtils.setField(cartItem, "quantity", QUANTITY);

        return cartItem;
    }

    public static CartItemResponseDto createCartItemResponseDto() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
