package salute.oneshot.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.request.UpdateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.OrderItemListResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderItem;
import salute.oneshot.domain.order.entity.OrderStatus;
import salute.oneshot.domain.product.entity.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderTestFactory {

    public static final int ORDER_ITEM_QUANTITY = 3;

    public static final Long ORDER_ID = 1L;
    public static final String ORDER_NUMBER = "23031114553289";
    public static final String NAME = "보드카 외 2개";
    public static final Long AMOUNT = 120000L;
    public static final String UPDATED_STATUS = "PROCESSING";
    public static final OrderStatus STATUS = OrderStatus.PENDING_PAYMENT;
    public static final LocalDateTime ORDER_DATE = LocalDateTime.parse("2025-03-10T16:08:17.783333");

    public static OrderItemListResponseDto createOrderItemListResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<OrderItemListResponseDto> constructor =
                OrderItemListResponseDto.class.getDeclaredConstructor(String.class, int.class, int.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ProductTestFactory.NAME, ORDER_ITEM_QUANTITY, ProductTestFactory.PRICE);
    }

    public static CreateOrderRequestDto createCreateOrderRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateOrderRequestDto> constructor =
                CreateOrderRequestDto.class.getDeclaredConstructor(Long.class);
        constructor.setAccessible(true);

        return constructor.newInstance(AddressTestFactory.ADDRESS_ID);
    }

    public static CreateOrderResponseDto createCreateOrderResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateOrderResponseDto> constructor =
                CreateOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, Long.class, OrderStatus.class, LocalDateTime.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ORDER_ID, ORDER_NUMBER, NAME, AMOUNT, STATUS, ORDER_DATE);
    }

    public static GetOrderResponseDto createGetOrderResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<GetOrderResponseDto> constructor =
                GetOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, Long.class, List.class);
        constructor.setAccessible(true);

        List<OrderItemListResponseDto> orderItems = new ArrayList<>();
        orderItems.add(createOrderItemListResponseDto());

        return constructor.newInstance(ORDER_ID, ORDER_NUMBER, AMOUNT, orderItems);
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

        return constructor.newInstance(UPDATED_STATUS);
    }

    public static UpdateOrderResponseDto createUpdateOrderResponseDto() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<UpdateOrderResponseDto> constructor =
                UpdateOrderResponseDto.class.getDeclaredConstructor(Long.class, String.class, Long.class, OrderStatus.class, LocalDateTime.class);
        constructor.setAccessible(true);

        return constructor.newInstance(ORDER_ID, NAME, AMOUNT, STATUS, ORDER_DATE);
    }

    public static Order createOrder() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Product product = ProductTestFactory.createProduct();
        OrderItem orderItem = OrderItem.of(
                null, product,
                3,
                product.getPrice() * CartTestFactory.QUANTITY);
        List<OrderItem> orderItemList = List.of(orderItem);

        Order order = Order.of(
                ORDER_NUMBER,
                NAME,
                AMOUNT,
                UserTestFactory.createUser(),
                CartTestFactory.createCart(),
                AddressTestFactory.createAddress(),
                orderItemList);

        ReflectionTestUtils.setField(order, "id", 1L);

        return order;
    }
}
