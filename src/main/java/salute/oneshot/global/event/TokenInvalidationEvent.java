package salute.oneshot.global.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInvalidationEvent {
    private final String token;

    public static TokenInvalidationEvent of(String token) {
        return new TokenInvalidationEvent(token);
    }
}
