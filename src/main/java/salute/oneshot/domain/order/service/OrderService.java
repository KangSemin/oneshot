package salute.oneshot.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.cart.entity.CartItem;
import salute.oneshot.domain.cart.repository.CartRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.OrderItemListResponseDto;
import salute.oneshot.domain.order.dto.response.OrderResponseDto;
import salute.oneshot.domain.order.dto.service.CreateOrderSDto;
import salute.oneshot.domain.order.dto.service.GetOrderSDto;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderItem;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderSDto sDto) {

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

        // 주문 생성
        Order order = Order.of(orderName, orderAmount, cart.getUser(), cart, address, orderItems);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        // 주문 저장
        orderRepository.save(order);

        // 카트 상태 변경
        cart.setIsOrdered();

        return OrderResponseDto.from(order);
    }

    @Transactional
    public GetOrderResponseDto getOrder(GetOrderSDto sDto) {

        Order order = orderRepository.findById(sDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if(!order.getUser().getId().equals(sDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.ORDER_GET_FORBIDDEN);
        }

        order.getCart();

        List<OrderItemListResponseDto> responseDtoList = order.getOrderItems().stream()
                .map(OrderItemListResponseDto::from).collect(Collectors.toList());

        return GetOrderResponseDto.from(order, responseDtoList);
    }


    private String generateOrderName(Cart cart) {

        if (cart.getItemList().size() == 1) {
            return cart.getItemList().get(0).getProduct().getName();
        } else {
            return cart.getItemList().get(0).getProduct().getName() + " 외 " +
                    (cart.getItemList().size() - 1) + "개";
        }


    }

}