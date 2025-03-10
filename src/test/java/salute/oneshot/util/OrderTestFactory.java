package salute.oneshot.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.request.UpdateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.OrderItemListResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.entity.OrderStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderTestFactory {
    private static final Long ADDRESS_ID = 1L;
    private static final Long ORDER_ID = 1L;

    public static OrderItemListResponseDto createOrderItemListResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<OrderItemListResponseDto> constructor =
                OrderItemListResponseDto.class.getDeclaredConstructor(String.class, int.class, int.class);
        constructor.setAccessible(true);

        return constructor.newInstance("보드카", 3, 12000);
    }

    public static CreateOrderRequestDto createCreateOrderRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateOrderRequestDto> constructor =
                CreateOrderRequestDto.class.getDeclaredConstructor(Long.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ADDRESS_ID);
    }

    public static CreateOrderResponseDto createCreateOrderResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateOrderResponseDto> constructor =
                CreateOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, Long.class, OrderStatus.class, LocalDateTime.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ORDER_ID, "23031114553289", "보드카 외 2개", 120000L, OrderStatus.PENDING_PAYMENT, LocalDateTime.parse("2025-03-10T16:08:17.783333"));
    }

    public static GetOrderResponseDto createGetOrderResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<GetOrderResponseDto> constructor =
                GetOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, Long.class, List.class);
        constructor.setAccessible(true);

        List<OrderItemListResponseDto> orderItems = new ArrayList<>();
        orderItems.add(createOrderItemListResponseDto());

        return constructor.newInstance(ORDER_ID, "23031114553289", 120000L, orderItems);
    }

    public static Page<CreateOrderResponseDto> createCreateOrderResponseDtoPage() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<CreateOrderResponseDto> createOrderResponseDtoList = new ArrayList<>();
        createOrderResponseDtoList.add(createCreateOrderResponseDto());

        return new PageImpl<>(createOrderResponseDtoList);
    }

    public static UpdateOrderRequestDto createUpdateOrderRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateOrderRequestDto> constructor =
                UpdateOrderRequestDto.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        return constructor.newInstance("PROCESSING");
    }

    public static UpdateOrderResponseDto createUpdateOrderResponseDto() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<UpdateOrderResponseDto> constructor =
                UpdateOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, Long.class, OrderStatus.class, LocalDateTime.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ORDER_ID, "보드카 외 2개", 120000L, OrderStatus.PENDING_PAYMENT, LocalDateTime.parse("2025-03-10T16:08:17.783333"));
    }
}
