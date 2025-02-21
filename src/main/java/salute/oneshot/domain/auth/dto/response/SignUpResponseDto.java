package salute.oneshot.domain.auth.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpResponseDto {

    private final Long id;
    private final String email;
    private final String nickName;

    public static SignUpResponseDto from(User user) {
        return new SignUpResponseDto(
                user.getId(),
                user.getEmail(),
                user.getNickName()
        );
    }
}
