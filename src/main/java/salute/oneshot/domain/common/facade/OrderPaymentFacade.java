package salute.oneshot.domain.common.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderDetailsResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.dto.service.*;
import salute.oneshot.domain.order.service.OrderService;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.domain.payment.service.PaymentService;

@Component
@RequiredArgsConstructor
public class OrderPaymentFacade {
    private final OrderService orderService;
    private final PaymentService paymentService;

    // OrderService
    public CreateOrderResponseDto createOrder(CreateOrderSDto sDto) {
        return orderService.createOrder(sDto);
    }

    public GetOrderResponseDto getOrder(GetOrderSDto sDto) {
        return orderService.getOrder(sDto);
    }

    public Page<CreateOrderResponseDto> getAllOrder(GetAllOrderSDto sDto) {
        return orderService.getAllOrder(sDto);
    }

    public UpdateOrderResponseDto updateOrder(UpdateOrderSDto sDto) {
        return orderService.updateOrder(sDto);
    }

    @Transactional
    public void deleteOrder(DeleteOrderSDto sDto) {
        orderService.deleteOrder(sDto);
        paymentService.cancelPayment("구매자가 주문을 취소했습니다.");
    }

    @Transactional
    public GetOrderDetailsResponseDto getOrderDetails(GetOrderDetailsSDto sDto) {
        return orderService.getOrderDetails(sDto);
    }

    // PaymentService
    public PaymentResponseDto confirmPayment(ConfirmPaymentSDto sDto) {
        PaymentResponseDto paymentResponseDto = paymentService.confirmPayment(sDto);
        orderService.updateOrderStatusAfterPaymentIsDone(sDto.getOrderNumber());
        return paymentResponseDto;
    }

    public PaymentResponseDto getPayment(String paymentKey) {
        return paymentService.getPayment(paymentKey);
    }
}
