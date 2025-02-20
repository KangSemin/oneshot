package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.domain.order.dto.response.GetOrderDetailsResponseDto;
import salute.oneshot.domain.order.dto.service.GetOrderDetailsSDto;
import salute.oneshot.domain.order.service.OrderService;
import salute.oneshot.domain.payment.service.PaymentService;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentViewController {

    private final PaymentService paymentService;
    private final OrderService orderService;
    // 추후 제거
    private final PaymentClient paymentClient;

    @GetMapping("/orders/{orderId}/payments")
    public String paymentsPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId,
            Model model
    ) {

        GetOrderDetailsResponseDto orderResponseDto = orderService.getOrderDetails(GetOrderDetailsSDto.of(userDetails.getId(), orderId));

        model.addAttribute("order", orderResponseDto);

        return "payment/toss-payment";
    }

//    // 테스트 혹은 로그 용도? -> OrderService.getOrderDetails()로 이동?
//    private String getPaymentStatus(String orderNumber) {
//        try {
//            TossPayment tossResponseDto = paymentClient.getPaymentByOrderNumber(orderNumber);
//            return tossResponseDto.getStatus().toString();
//        } catch (Exception e) {
//            return "NOT_FOUND_PAYMENT";
//        }
//    }

    @GetMapping("/payments/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @GetMapping("/payments/fail")
    public String paymentFail() {
        return "payment/fail";
    }

}
