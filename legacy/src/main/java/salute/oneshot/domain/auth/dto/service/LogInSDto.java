package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogInSDto {

    private final String email;
    private final String password;

    public static LogInSDto of(String email, String password) {
        return new LogInSDto(email, password);
    }
}
