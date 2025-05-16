package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteUserSDto {

    private final Long userId;
    private final String token;

    public static DeleteUserSDto of(Long userId, String token) {
        return new DeleteUserSDto(userId, token);
    }
}
