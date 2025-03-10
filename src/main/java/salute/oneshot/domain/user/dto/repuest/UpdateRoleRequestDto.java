package salute.oneshot.domain.user.dto.repuest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoleRequestDto {

    private final String userRole;

    public static UpdateRoleRequestDto of(String userRole) {
        return new UpdateRoleRequestDto(userRole);
    }
}
