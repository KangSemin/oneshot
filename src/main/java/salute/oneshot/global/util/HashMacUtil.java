package salute.oneshot.global.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
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
            log.error("HMAC_ALGO 관련 알 수 없는 에러발생 : {}", e.getMessage());
            return null;
        }
    }

    public boolean validValue(String uuid, String signature) {

        if (uuid == null || signature == null) {
            return false;
        }
        String expectedSig = createSignature(uuid);
        return expectedSig.equals(signature);
    }
}

