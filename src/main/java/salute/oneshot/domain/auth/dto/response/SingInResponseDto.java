package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SingInResponseDto {

    private final String accessToken;
    private final String tokenType;
}


