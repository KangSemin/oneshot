package salute.oneshot.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.service.UpdateRoleSDto;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRoleResponseDto {

    private final Long userId;
    private final UserRole userRole;

    public static UserRoleResponseDto of(
            Long userId,
            UserRole userRole
    ) {
        return new UserRoleResponseDto(userId, userRole);
    }
}
