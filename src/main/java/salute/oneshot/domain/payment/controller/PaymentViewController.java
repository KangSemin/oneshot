package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.domain.payment.service.PaymentService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaymentViewController {

    private final PaymentService paymentService;

    @GetMapping("/orders/{orderId}/payments")
    public String paymentsPage(
            @PathVariable Long orderId,
            Model model
    ) {
        model.addAttribute("orderId", "order-" + orderId);
        model.addAttribute("orderName", "맛있는 거 먹었다");
        model.addAttribute("customerEmail", "tpals@tpals.com");
        model.addAttribute("customerName", "강세민");
        model.addAttribute("userId", "user-" + 1L);
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
