package salute.oneshot.util;

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

    public static User createUser(String email, String encodedPassword, String nickname) {
        return User.of(email, encodedPassword, nickname);
    }

    public static SignUpSDto createSignUpSDto() {
        return SignUpSDto.of(EMAIL, RAW_PASSWORD, NICKNAME);
    }

    public static LogInSDto createLogInSDto() {
        return LogInSDto.of(EMAIL, RAW_PASSWORD);
    }

    public static LogOutSDto createLogOutSDto() {
        return LogOutSDto.of(USER_ID, TokenTestFactory.ACCESS_TOKEN);
    }
}
