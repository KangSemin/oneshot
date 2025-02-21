package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogOutSDto {

    private final Long id;
    private final String token;

    public static LogOutSDto of(Long id, String token) {
        return new LogOutSDto(id, token);
    }
}
