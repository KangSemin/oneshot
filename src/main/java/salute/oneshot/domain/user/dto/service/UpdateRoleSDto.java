package salute.oneshot.domain.user.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoleSDto {

    private final Long userId;
    private final UserRole userRole;

    public static UpdateRoleSDto of(
            Long userId,
            UserRole userRole
    ) {
        return new UpdateRoleSDto(userId, userRole);
    }
}
