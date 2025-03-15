package salute.oneshot.domain.payment.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import salute.oneshot.domain.payment.dto.feign.TossCancelPaymentRequestDto;
import salute.oneshot.domain.payment.dto.feign.TossConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.entity.TossPayment;
import salute.oneshot.global.config.FeignConfig;

//@FeignClient(name = "paymentClient", url = "${payment.base-url}", configuration = PaymentFeignConfig.class)
@FeignClient(name = "paymentClient", url = "https://api.tosspayments.com/v1/payments", configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping(value = "/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    TossPayment confirmPayment(@RequestBody TossConfirmPaymentRequestDto requestDto);

    @GetMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    TossPayment getPaymentByOrderId(@PathVariable String orderId);

    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
    TossPayment cancelPayment(@RequestBody TossCancelPaymentRequestDto requestDto);

}