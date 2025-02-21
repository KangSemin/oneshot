package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.order.dto.response.GetOrderDetailsResponseDto;
import salute.oneshot.domain.order.dto.service.GetOrderDetailsSDto;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentViewController {

    private final OrderPaymentFacade orderPaymentFacade;

    @GetMapping("/orders/{orderId}/payments")
    public String paymentsPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId,
            Model model
    ) {

        GetOrderDetailsResponseDto orderResponseDto = orderPaymentFacade.getOrderDetails(GetOrderDetailsSDto.of(userDetails.getId(), orderId));

        model.addAttribute("order", orderResponseDto);

        return "payment/toss-payment";
    }

    @GetMapping("/payments/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @GetMapping("/payments/fail")
    public String paymentFail() {
        return "payment/fail";
    }
}
