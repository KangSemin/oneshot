package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateSDto {

    private final Long id;
    private final String nickName;
    private final String password;

    public static UserUpdateSDto of(
            Long id,
            String nickName,
            String password
    ) {
        return new UserUpdateSDto(id, nickName, password);
    }
}
