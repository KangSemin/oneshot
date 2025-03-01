package salute.oneshot.domain.auth.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum OAuthProvider {
    NAVER, GOOGLE;

    public static OAuthProvider of(String userRole) {
        return Arrays.stream(OAuthProvider.values())
                .filter(r -> r.name().equalsIgnoreCase(userRole))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_PROVIDER_NAME));
    }
}
