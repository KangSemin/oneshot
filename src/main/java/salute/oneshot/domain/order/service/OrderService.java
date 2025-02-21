package salute.oneshot.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.cart.repository.CartRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.OrderItemListResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.*;
import salute.oneshot.domain.order.dto.service.*;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderItem;
import salute.oneshot.domain.order.entity.OrderStatus;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.CustomRuntimeException;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateOrderResponseDto createOrder(CreateOrderSDto sDto) {

        //유저 아이디로 장바구니가 존재 하는지 조회
        Cart cart = cartRepository.findByUserIdAndIsOrderedFalse(sDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.CART_NOT_FOUND));

        // 유저 아이디로 주소 아이디가 존재하는지 확인
        Address address = addressRepository.findById(sDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        if (!address.getUserId().equals(sDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.INVALID_ADDRESS_ACCESS);
        }

        // 카트에서 주문 아이템으로 변경
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItemList()) {
            Product product = cartItem.getProduct();
            int amount = product.getPrice() * cartItem.getQuantity();
            OrderItem orderItem = OrderItem.of(null, product, cartItem.getQuantity(), amount);
            orderItems.add(orderItem);
        }

        String orderName = generateOrderName(cart);
        long orderAmount = orderItems.stream().mapToLong(OrderItem::getPrice).sum();

        //주문번호 생성
        String orderNumber = generateOrderNumber();

        // 주문 생성
        Order order = Order.of(orderNumber ,orderName, orderAmount, cart.getUser(), cart, address, orderItems);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        // 주문 저장
        orderRepository.save(order);

        // 카트 상태 변경
        cart.setIsOrdered();

        return CreateOrderResponseDto.from(order);
    }

    @Transactional
    public GetOrderResponseDto getOrder(GetOrderSDto sDto) {

        Order order = orderRepository.findById(sDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if (!order.getUser().getId().equals(sDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.ORDER_GET_FORBIDDEN);
        }

        List<OrderItemListResponseDto> responseDtoList = order.getOrderItems().stream()
                .map(OrderItemListResponseDto::from).collect(Collectors.toList());

        return GetOrderResponseDto.from(order, responseDtoList);
    }

    @Transactional
    public Page<CreateOrderResponseDto> getAllOrder(GetAllOrderSDto sDto) {

        Page<Order> orderPage = orderRepository.findByUser_Id(sDto.getUserId(), sDto.getPageable());

        Page<CreateOrderResponseDto> responseDtoPage = orderPage.map(CreateOrderResponseDto::from);

        return responseDtoPage;
    }

    @Transactional
    public UpdateOrderResponseDto updateOrder(UpdateOrderSDto sDto) {

        Order order = orderRepository.findById(sDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        User user = getUserById(sDto.getUserId());
        verifyAdmin(user);

        if (!order.isValidStatusChange(order.getStatus(), OrderStatus.valueOf(sDto.getOrderStatus()))) {
            throw new InvalidException(ErrorCode.INVALID_ORDER_STATUS);
        }

        order.updateOrderStatus(OrderStatus.valueOf(sDto.getOrderStatus()));

        return UpdateOrderResponseDto.from(order);
    }

    @Transactional
    public void deleteOrder(DeleteOrderSDto sDto) {

        Order order = orderRepository.findById(sDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if (!order.getUser().getId().equals(sDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.ORDER_CANCEL_FORBIDDEN);
        }

        if (order.getStatus() == OrderStatus.SHIPPED) {

            throw new CustomRuntimeException(ErrorCode.CANNOT_CANCEL_SHIPPED_ORDER);

        } else if (order.getStatus() == OrderStatus.CANCELLED) {

            throw new CustomRuntimeException(ErrorCode.ALREADY_CANCELLED_ORDER);
        }

        order.updateOrderStatus(OrderStatus.CANCELLED);
    }


    public GetOrderDetailsResponseDto getOrderDetails(GetOrderDetailsSDto sDto) {
        Order order = orderRepository.findByIdAndUserId(sDto.getOrderId(), sDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        return GetOrderDetailsResponseDto.from(order);
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private void verifyAdmin(User user) {
        if (user.getUserRole() != UserRole.ADMIN) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    private String generateOrderName(Cart cart) {

        if (cart.getItemList().size() == 1) {
            return cart.getItemList().get(0).getProduct().getName();
        } else {
            return cart.getItemList().get(0).getProduct().getName() + " 외 " +
                    (cart.getItemList().size() - 1) + "개";
        }
    }

    private String generateOrderNumber() {

        String orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));

        String chars = "0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            int nextInt = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(nextInt));
        }
        return orderDate + stringBuilder.toString();
    }

}