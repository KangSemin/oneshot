package salute.oneshot.domain.user.dto.repuest;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserRequestDto {
    @Size(
            min = UserValidationConst.NICKNAME_MIN,
            max = UserValidationConst.NICKNAME_MAX,
            message = UserValidationConst.NICKNAME_RANGE_MESSAGE
    )
    private final String nickname;

    @Pattern(
            regexp = UserValidationConst.PASSWORD_REG,
            message = UserValidationConst.INVALID_PASSWORD_MESSAGE
    )
    private final String password;
}
