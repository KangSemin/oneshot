package salute.oneshot.domain.auth.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpSDto {

    private final String email;
    private final String password;
    private final String nickName;

    public static SignUpSDto from(SignUpRequestDto requestDto) {
        return new SignUpSDto(
            requestDto.getEmail(),
            requestDto.getPassword(),
            requestDto.getNickName()
        );
    }
}

