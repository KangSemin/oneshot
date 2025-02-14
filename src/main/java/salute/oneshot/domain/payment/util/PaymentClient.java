package salute.oneshot.domain.payment.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.global.config.PaymentFeignConfig;

@FeignClient(name = "PaymentClient", url = "${spring.payment.base-url}", configuration = PaymentFeignConfig.class)
public interface PaymentClient {

    @PostMapping(value = "/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    ConfirmPaymentResponseDto confirmPayment(@RequestBody ConfirmPaymentSDto sdto);
}
