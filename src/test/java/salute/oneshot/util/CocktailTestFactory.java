package salute.oneshot.util;

import salute.oneshot.domain.cocktail.entity.RecipeType;

import java.time.LocalDateTime;

public class CocktailTestFactory {
    public static final Long COCKTAIL_ID = 1L;
    public static final String NAME = "연태하이볼";
    public static final String DESCRIPTION = "저만의 레시피입니다.";
    public static final String RECIPE = "탄산수를 넣어줍니다.";
    public static final RecipeType TYPE = RecipeType.CUSTOM;
    public static final Integer LIKE_COUNT = 100;
    public static final Double STAR_RATE = 3.0;
    public static final Integer VIEW_COUNT = 10000;
    public static final LocalDateTime CREATED_AT = LocalDateTime.parse("2025-03-10T16:08:17.783333");
    public static final LocalDateTime MODIFIED_AT = LocalDateTime.parse("2025-03-10T16:08:17.783333");
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
}
