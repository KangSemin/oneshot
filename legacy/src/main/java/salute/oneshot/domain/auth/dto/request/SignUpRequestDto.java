package salute.oneshot.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.dto.UserValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    @NotBlank(message = UserValidationConst.EMAIL_BLANK_MESSAGE)
    @Pattern(
            regexp = UserValidationConst.EMAIL_REG,
            message = UserValidationConst.INVALID_EMAIL_MESSAGE)
    private final String email;

    @NotBlank(message = UserValidationConst.PASSWORD_BLANK_MESSAGE)
    @Size(min = UserValidationConst.PASSWORD_MIN, message = UserValidationConst.PASSWORD_MIN_MESSAGE)
    @Pattern(
            regexp = UserValidationConst.PASSWORD_REG,
            message = UserValidationConst.INVALID_PASSWORD_MESSAGE)
    private final String password;

    @NotBlank(message = UserValidationConst.NICKNAME_BLANK_MESSAGE)
    @Size(
            min = UserValidationConst.NICKNAME_MIN,
            max = UserValidationConst.NICKNAME_MAX,
            message = UserValidationConst.NICKNAME_RANGE_MESSAGE)
    private final String nickName;
}