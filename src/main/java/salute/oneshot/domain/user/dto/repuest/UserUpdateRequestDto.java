package salute.oneshot.domain.user.dto.repuest;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationMessage;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateRequestDto {

    @Size(
            min = UserValidationMessage.NICKNAME_MIN,
            max = UserValidationMessage.NICKNAME_MAX,
            message = UserValidationMessage.NICKNAME_RANGE_MESSAGE
    )
    private final String nickName;

    @Size(min = UserValidationMessage.PASSWORD_MIN, message = UserValidationMessage.PASSWORD_MIN_MESSAGE)
    @Pattern(
            regexp = UserValidationMessage.PASSWORD_REG,
            message = UserValidationMessage.INVALID_PASSWORD_MESSAGE
    )
    private final String password;
}
