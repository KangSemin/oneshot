package salute.oneshot.global.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
@Getter
@RequiredArgsConstructor
public class NonceGenerator {

    private final String nonce;

    public NonceGenerator() {
        this.nonce = generateNonce();
    }

    private String generateNonce() {
        try {
            return Base64.getEncoder().encodeToString(
                    SecureRandom.getInstance("SHA1PRNG").generateSeed(16)
            );
        } catch (NoSuchAlgorithmException e) {
            return "default-nonce-" + System.currentTimeMillis();
        }
    }
}