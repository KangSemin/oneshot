package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenDto {

    private final String refreshToken;

    public static RefreshTokenDto of(String refreshToken) {
        return new RefreshTokenDto(refreshToken);
    }
}
