package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.domain.payment.dto.feign.TossPaymentResponseDto;
import salute.oneshot.domain.payment.service.PaymentService;
import salute.oneshot.domain.payment.util.PaymentClient;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentViewController {

    private final PaymentService paymentService;
    //
    private final PaymentClient paymentClient;

    @GetMapping("/orders/{orderId}/payments")
    public String paymentsPage(
            @PathVariable Long orderId,
            Model model
    ) {
        String orderIdString = "order-" + orderId;
        String status;
        try {
            TossPaymentResponseDto tossResponseDto = paymentClient.getPaymentByOrderId(orderIdString);
            status = tossResponseDto.getStatus();
        } catch (Exception e) {
            status = "NOT_FOUND_PAYMENT";
        }
        model.addAttribute("status", status);

        model.addAttribute("orderId", orderIdString);
        model.addAttribute("orderName", "맛있는 거 먹었다");
        model.addAttribute("customerEmail", "tpals@tpals.com");
        model.addAttribute("customerName", "강세민");
        model.addAttribute("userId", "user-" + 1L);
        model.addAttribute("amountValue", 12500L);
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
