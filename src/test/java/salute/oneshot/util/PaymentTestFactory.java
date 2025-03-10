package salute.oneshot.util;

import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.entity.PaymentStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PaymentTestFactory {
    public static final String PAYMENT_KEY = "some-payment-key";

    public static ConfirmPaymentRequestDto createConfirmPaymentRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<ConfirmPaymentRequestDto> constructor =
                ConfirmPaymentRequestDto.class.getDeclaredConstructor(String.class, Long.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance("23031114553289", 120000L, PAYMENT_KEY);
    }

    public static PaymentResponseDto createPaymentResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<PaymentResponseDto> constructor =
                PaymentResponseDto.class.getDeclaredConstructor(String.class, PaymentStatus.class, String.class, String.class, Long.class);
        constructor.setAccessible(true);

        return constructor.newInstance(PAYMENT_KEY, PaymentStatus.DONE, "23031114553289", "보드카 외 2개", 120000L);
    }
}
