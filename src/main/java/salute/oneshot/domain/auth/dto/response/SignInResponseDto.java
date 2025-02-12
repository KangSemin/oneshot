package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponseDto {

    private final String accessToken;
    private final String tokenType;

    public static SignInResponseDto of(String accessToken, String tokenType) {
        return new SignInResponseDto(accessToken ,tokenType);
    }
}
