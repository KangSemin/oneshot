package salute.oneshot.domain.payment.util;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import java.beans.ConstructorProperties;

@Getter
@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {
    private String SecretKey;
}
