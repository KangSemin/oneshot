package salute.oneshot.global.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInvalidationEvent {
    private final String token;
    private final long expirationTime;

    public static TokenInvalidationEvent of(String token, long expirationTime) {
        return new TokenInvalidationEvent(token, expirationTime);
    }
}
