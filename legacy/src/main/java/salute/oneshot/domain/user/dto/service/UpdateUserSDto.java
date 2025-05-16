package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserSDto {

    private final Long id;
    private final String nickname;
    private final String password;

    public static UpdateUserSDto of(
            Long id,
            String nickname,
            String password
    ) {
        return new UpdateUserSDto(id, nickname, password);
    }
}
