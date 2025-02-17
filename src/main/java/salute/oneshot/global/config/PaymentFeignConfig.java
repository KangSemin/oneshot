package salute.oneshot.global.config;

import feign.Request;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import salute.oneshot.domain.payment.util.PaymentAuthInterceptor;
import salute.oneshot.domain.payment.util.PaymentClient;
import salute.oneshot.domain.payment.util.PaymentErrorDecoder;
import salute.oneshot.domain.payment.util.PaymentProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(clients = PaymentClient.class)
@EnableConfigurationProperties(PaymentProperties.class)
public class PaymentFeignConfig {

    private final PaymentProperties paymentProperties;

    public PaymentFeignConfig(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

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
        return new PaymentAuthInterceptor(paymentProperties);
    }

//    @Bean
//    PaymentLoggingInterceptor paymentLoggingInterceptor() {
//        return new PaymentLoggingInterceptor();
//    }
}