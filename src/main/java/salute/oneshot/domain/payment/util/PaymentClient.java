package salute.oneshot.domain.payment.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.global.config.PaymentFeignConfig;

//@FeignClient(name = "paymentClient", url = "${payment.base-url}", configuration = PaymentFeignConfig.class)
@FeignClient(name = "paymentClient", url = "https://api.tosspayments.com/v1/payments", configuration = PaymentFeignConfig.class)
public interface PaymentClient {

    @PostMapping(value = "/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    ConfirmPaymentResponseDto confirmPayment(@RequestBody ConfirmPaymentSDto sdto);

}