package salute.oneshot.util;

import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IngredientTestFactory {
    public static final Long INGREDIENT_ID = 1L;
    public static final String NAME = "라임";
    public static final Double AVB = 0.0;
    public static final String DESCRIPTION = "과일의 한 종류입니다.";
    public static final IngredientCategory CATEGORY = IngredientCategory.OTHER;
//    public static final PaymentStatus STATUS = PaymentStatus.DONE;
//
//    public static ConfirmPaymentRequestDto createConfirmPaymentRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<ConfirmPaymentRequestDto> constructor =
//                ConfirmPaymentRequestDto.class.getDeclaredConstructor(String.class, Long.class, String.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(OrderTestFactory.NAME, OrderTestFactory.AMOUNT, PAYMENT_KEY);
//    }
//
//    public static PaymentResponseDto createPaymentResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<PaymentResponseDto> constructor =
//                PaymentResponseDto.class.getDeclaredConstructor(String.class, PaymentStatus.class, String.class, String.class, Long.class);
//        constructor.setAccessible(true);
//
//        return constructor.newInstance(PAYMENT_KEY, STATUS, OrderTestFactory.ORDER_NUMBER, OrderTestFactory.NAME, OrderTestFactory.AMOUNT);
//    }

    public static IngredientResponseDto createIngredientResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<IngredientResponseDto> constructor =
                IngredientResponseDto.class.getDeclaredConstructor(Long.class, String.class, Double.class, String.class, IngredientCategory.class);
        constructor.setAccessible(true);

        return constructor.newInstance(INGREDIENT_ID, NAME, AVB, DESCRIPTION, CATEGORY);
    }
}
