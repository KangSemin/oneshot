package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.order.dto.response.GetOrderDetailsResponseDto;
import salute.oneshot.domain.order.dto.service.GetOrderDetailsSDto;
import salute.oneshot.global.config.NonceGenerator;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentViewController {

    private final OrderPaymentFacade orderPaymentFacade;
    private final NonceGenerator nonceGenerator;

    @GetMapping("/orders/{orderId}/payments")
    public String paymentsPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId,
            Model model
    ) {
        // 시큐리티 CSP 설정용
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());

        GetOrderDetailsResponseDto orderResponseDto = orderPaymentFacade.getOrderDetails(GetOrderDetailsSDto.of(userDetails.getId(), orderId));

        model.addAttribute("order", orderResponseDto);

        return "payment/toss-payment";
    }

    @GetMapping("/payments/success")
    public String paymentSuccessPage(Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        return "payment/success";
    }

    @GetMapping("/payments/fail")
    public String paymentFailPage(Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        return "payment/fail";
    }
}
