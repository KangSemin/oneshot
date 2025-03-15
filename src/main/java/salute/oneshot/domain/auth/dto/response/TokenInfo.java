package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

    private final String accessToken;
    private final String refreshToken;
    private final long accessExpiresAt;

    public static TokenInfo of(
            String accessToken,
            String refreshToken,
            long expiresAt
    ) {
        return new TokenInfo(
                accessToken,
                refreshToken,
                expiresAt);
    }
}
