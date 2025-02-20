package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignOutSDto {

    private final Long id;
    private final String token;

    public static SignOutSDto of(Long id, String token) {
        return new SignOutSDto(id, token);
    }
}
