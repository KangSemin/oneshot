package salute.oneshot.domain.user.dto.repuest;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationConst;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoleRequestDto {

    @NotBlank(message = UserValidationConst.USER_ROLE_MESSAGE)
    private final String userRole;
}
