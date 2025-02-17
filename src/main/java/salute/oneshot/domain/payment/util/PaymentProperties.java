package salute.oneshot.domain.payment.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {
    private final String secretKey;
//    private final String base;
//    private final String confirmEndpoint;
}
