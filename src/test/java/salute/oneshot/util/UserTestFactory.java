package salute.oneshot.util;

import org.springframework.security.core.parameters.P;
import salute.oneshot.domain.auth.dto.request.LogInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;

public class UserTestFactory {

    public static final Long USER_ID = 1L;
    public static final UserRole ROLE_USER = UserRole.USER;
    public static final UserRole ROLE_ADMIN = UserRole.ADMIN;
    public static final String EMAIL = "test@mail.com";
    public static final String RAW_PASSWORD = "rawPassword123!";
    public static final String ENCODED_PASSWORD = "encodedPassword123!";
    public static final String NICKNAME = "nickname";

    public static User createUser() {
        return User.of(EMAIL, ENCODED_PASSWORD, NICKNAME);
    }

    public static SignUpSDto createSignUpSDto() {
        return SignUpSDto.of(EMAIL, RAW_PASSWORD, NICKNAME);
    }

    public static SignUpSDto createSignUpSDto(String email, String rawPassword, String nickname) {
        return SignUpSDto.of(email, rawPassword, nickname);
    }

    public static LogInSDto createLogInSDto() {
        return LogInSDto.of(EMAIL, RAW_PASSWORD);
    }

    public static LogInSDto createLogInSDto(String email, String rawPassword) {
        return LogInSDto.of(email, rawPassword);
    }

    public static LogOutSDto createLogOutSDto() {
        return LogOutSDto.of(USER_ID, TokenTestFactory.ACCESS_TOKEN);
    }

    public static LogOutSDto createLogOutSDto(Long userId, String accessToken) {
        return LogOutSDto.of(USER_ID, TokenTestFactory.ACCESS_TOKEN);
    }

    public static SignUpRequestDto createSignUpRequestDto() {
        return SignUpRequestDto.of(EMAIL, RAW_PASSWORD, NICKNAME);
    }

    public static LogInRequestDto createLogInRequestDto() {
        return LogInRequestDto.of(EMAIL, RAW_PASSWORD);
    }

    public static SignUpResponseDto createSignUpResponseDto(User user) {
        return SignUpResponseDto.from(user);
    }
}
