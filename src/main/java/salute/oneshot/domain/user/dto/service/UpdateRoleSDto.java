package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoleSDto {

    private final Long userId;
    private final UserRole role;

    public static UpdateRoleSDto of(
            Long userId,
            UserRole role
    ) {
        return new UpdateRoleSDto(userId, role);
    }
}
