package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenDto {

    private final String accessToken;
    private final long expiresAt;

    public static AccessTokenDto from(TokenInfo tokenInfo) {
        return new AccessTokenDto(
                tokenInfo.getAccessToken(),
                tokenInfo.getAccessExpiresAt());
    }
}
