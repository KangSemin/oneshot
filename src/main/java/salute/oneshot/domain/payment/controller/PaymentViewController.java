package salute.oneshot.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.domain.payment.service.PaymentService;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentViewController {

    private final PaymentService paymentService;

    @GetMapping
    public String paymentsPage() {
        return "payment/toss-payment";
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @GetMapping("/fail")
    public String paymentFail() {
        return "payment/fail";
    }

}
