package salute.oneshot.domain.payment.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PaymentAuthInterceptor implements RequestInterceptor {
    private static final String AUTH_HEADER_PREFIX = "Basic ";

    @Value("${PAYMENT_SECRET_KEY}")
    private String secretKey;

    @Override
    public void apply(final RequestTemplate template) {
        final String authHeader = createPaymentAuthorizationHeader();
        template.header("Authorization", authHeader);
    }

    private String createPaymentAuthorizationHeader() {
        final byte[] encodedBytes = Base64.getEncoder().encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        return AUTH_HEADER_PREFIX + new String(encodedBytes);
    }
}