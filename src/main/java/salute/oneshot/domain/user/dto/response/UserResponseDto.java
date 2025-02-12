package salute.oneshot.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    private final Long id;
    private final String email;
    private final String nickName;
    private final UserRole userRole;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
            user.getId(),
            user.getEmail(),
            user.getNickName(),
            user.getUserRole()
        );
    }
}
