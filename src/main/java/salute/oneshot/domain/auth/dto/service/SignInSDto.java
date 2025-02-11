package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInSDto {

    private String email;
    private String password;

    public static SignInSDto of(String email, String password) {
        return new SignInSDto(email, password);
    }
}
