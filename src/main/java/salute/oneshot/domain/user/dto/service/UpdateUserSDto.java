package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserSDto {

    private final Long id;
    private final String nickName;
    private final String password;

    public static UpdateUserSDto of(
            Long id,
            String nickName,
            String password
    ) {
        return new UpdateUserSDto(id, nickName, password);
    }
}
