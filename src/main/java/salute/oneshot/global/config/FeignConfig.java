package salute.oneshot.global.config;

import feign.Request;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import salute.oneshot.domain.payment.util.PaymentAuthInterceptor;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.domain.payment.util.PaymentErrorDecoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(clients = PaymentClient.class)
public class FeignConfig {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(2, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true);
    }

    @Bean
    public PaymentErrorDecoder paymentErrorDecoder() {
        return new PaymentErrorDecoder();
    }

    @Bean
    PaymentAuthInterceptor paymentAuthInterceptor() {
        return new PaymentAuthInterceptor();
    }
}