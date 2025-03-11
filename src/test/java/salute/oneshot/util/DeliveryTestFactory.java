package salute.oneshot.util;


import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.delivery.dto.request.DeliveryRequestDto;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.enums.CourierCompany;
import salute.oneshot.domain.delivery.enums.ShippingStatus;

import java.lang.reflect.InvocationTargetException;
import java.sql.Ref;
import java.time.LocalDateTime;

public class DeliveryTestFactory {

    public static final Long DELIVERY_ID = 2L;
    public static final Long ORDER_ID = 1L;
    public static final String RECEIVER_NAVE = "봉미선";
    public static final String RECEIVER_PHONE = "010-1234-4567";
    public static final String DELIVERY_MESSAGE = "현관 비밀번호는 #0000#입니다.";
    public static final String TRACKING_NUMBER = "123456789";
    public static final String COURIER_COMPANY = "cjgls";

    public static Delivery createDelivery()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Delivery delivery = Delivery.of(
                OrderTestFactory.createOrder(),
                RECEIVER_NAVE,
                RECEIVER_PHONE,
                DELIVERY_MESSAGE,
                TRACKING_NUMBER,
                CourierCompany.ofName(COURIER_COMPANY));

        ReflectionTestUtils.setField(delivery, "id", DELIVERY_ID);
        ReflectionTestUtils.setField(delivery, "createdAt", LocalDateTime.parse("2025-03-11T00:00:00"));
        ReflectionTestUtils.setField(delivery, "status", ShippingStatus.REGISTERED);
        return delivery;
    }

    public static DeliveryRequestDto createDeliveryRequestDto() {
        return DeliveryRequestDto.of(
                ORDER_ID,
                RECEIVER_NAVE,
                RECEIVER_PHONE,
                DELIVERY_MESSAGE,
                TRACKING_NUMBER,
                COURIER_COMPANY);
    }
}
