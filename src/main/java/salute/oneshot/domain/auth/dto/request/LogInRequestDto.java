package salute.oneshot.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogInRequestDto {

    @NotBlank(message = UserValidationConst.EMAIL_BLANK_MESSAGE)
    @Pattern(
            regexp = UserValidationConst.EMAIL_REG,
            message = UserValidationConst.INVALID_EMAIL_MESSAGE)
    private final String email;

    @NotBlank(message = UserValidationConst.PASSWORD_BLANK_MESSAGE)
    private final String password;

    public static LogInRequestDto of(String email, String password) {
        return new LogInRequestDto(email, password);
    }
}
