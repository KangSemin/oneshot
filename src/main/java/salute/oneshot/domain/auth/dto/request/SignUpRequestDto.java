package salute.oneshot.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationMessage;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {
    @NotBlank(message = UserValidationMessage.EMAIL_BLANK_MESSAGE)
    @Pattern(
            regexp = UserValidationMessage.EMAIL_REG,
            message = UserValidationMessage.INVALID_EMAIL_MESSAGE
    )
    private final String email;

    @NotBlank(message = UserValidationMessage.PASSWORD_BLANK_MESSAGE)
    @Size(min = UserValidationMessage.PASSWORD_MIN, message = UserValidationMessage.PASSWORD_MIN_MESSAGE)
    @Pattern(
            regexp = UserValidationMessage.PASSWORD_REG,
            message = UserValidationMessage.INVALID_PASSWORD_MESSAGE
    )
    private final String password;

    @NotBlank(message = UserValidationMessage.NICKNAME_BLANK_MESSAGE)
    @Size(
            min = UserValidationMessage.NICKNAME_MIN,
            max = UserValidationMessage.NICKNAME_MAX,
            message = UserValidationMessage.NICKNAME_RANGE_MESSAGE
    )
    private final String nickName;
}