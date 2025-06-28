package salute.oneshot.global.util;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class HashMacUtil {

    @Value("${hmac.secret.key}")
    String secretKey;

    final String HMAC_ALGO = "HmacSHA256";

    public String createSignature(String message) {

        try {
            Mac hmac = Mac.getInstance(HMAC_ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_ALGO);
            hmac.init(keySpec);
            byte[] hash = hmac.doFinal(message.getBytes());
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("HMAC signing failed", e);
        }
    }

    public boolean validValue(String uuid, String signature) {

        if(uuid == null || signature == null){return false;}
        String expectedSig = createSignature(uuid);
        return expectedSig.equals(signature);
    }
}

