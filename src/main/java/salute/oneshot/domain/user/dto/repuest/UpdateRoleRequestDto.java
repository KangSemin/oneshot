package salute.oneshot.domain.user.dto.repuest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoleRequestDto {

    private final String userRole;
}
