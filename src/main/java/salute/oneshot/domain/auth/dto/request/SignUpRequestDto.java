package salute.oneshot.domain.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    private final String email;
    private final String password;
    private final String nickName;
}