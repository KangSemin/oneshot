package salute.oneshot.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationMessage;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInRequestDto {
    @NotBlank(message = UserValidationMessage.EMAIL_BLANK_MESSAGE)
    @Pattern(
            regexp = UserValidationMessage.EMAIL_REG,
            message = UserValidationMessage.INVALID_EMAIL_MESSAGE
    )
    private final String email;

    @NotBlank(message = UserValidationMessage.PASSWORD_BLANK_MESSAGE)
    private final String password;
}
