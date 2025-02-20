package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

    private final String accessToken;
    private final long accessExpiresAt;
    private final String refreshToken;
    private final long refreshExpiresAt;

    public static TokenInfo of(
            String accessToken,
            long expiresAt,
            String refreshToken,
            long refreshExpiresAt
    ) {
        return new TokenInfo(
                accessToken,
                expiresAt,
                refreshToken,
                refreshExpiresAt);
    }
}
