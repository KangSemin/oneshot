package salute.oneshot.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto implements Serializable {

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
