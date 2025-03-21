package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpSDto {

    private final String email;
    private final String password;
    private final String nickName;

    public static SignUpSDto of(
            String email,
            String password,
            String nickName
    ) {
      return new SignUpSDto(email, password, nickName);
    }
}

