package salute.oneshot.util;

import salute.oneshot.domain.auth.dto.response.TokenInfo;

public class TokenTestFactory {

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final long EXPIRES_AT = 1741341143L;
    public static final long TOKEN_REMAIN_TIME = 600L;

    public static TokenInfo createTokenInfo() {
        return TokenInfo.of(ACCESS_TOKEN, REFRESH_TOKEN, EXPIRES_AT);
    }
}
