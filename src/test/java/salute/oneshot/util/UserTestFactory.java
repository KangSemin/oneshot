package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.auth.dto.request.LogInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.user.dto.repuest.UpdateRoleRequestDto;
import salute.oneshot.domain.user.dto.repuest.UpdateUserRequestDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.model.CustomUserDetails;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UserTestFactory {

    public static final Long USER_ID = 1L;
    public static final UserRole ROLE_USER = UserRole.USER;
    public static final UserRole ROLE_ADMIN = UserRole.ADMIN;
    public static final String EMAIL = "test@mail.com";
    public static final String RAW_PASSWORD = "rawPassword123!";
    public static final String ENCODED_PASSWORD = "encodedPassword123!";
    public static final String NICKNAME = "nickname";

    public static User createUser() {
        User user = User.of(EMAIL, ENCODED_PASSWORD, NICKNAME);
        ReflectionTestUtils.setField(user,"id",USER_ID);

        return user;
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

    public static SignUpRequestDto createSignUpRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<SignUpRequestDto> userCon = SignUpRequestDto.class
                .getDeclaredConstructor(String.class, String.class, String.class);
        userCon.setAccessible(true);

        return userCon.newInstance(EMAIL, RAW_PASSWORD, NICKNAME);
    }

    public static LogInRequestDto createLogInRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<LogInRequestDto> userCon = LogInRequestDto.class
                .getDeclaredConstructor(String.class, String.class);
        userCon.setAccessible(true);

        return userCon.newInstance(EMAIL, RAW_PASSWORD);
    }

    public static UpdateRoleRequestDto createUpdateRoleRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<UpdateRoleRequestDto> userCon = UpdateRoleRequestDto.class
                .getDeclaredConstructor(String.class);
        userCon.setAccessible(true);

        return userCon.newInstance(ROLE_ADMIN.name());
    }

    public static UpdateUserRequestDto createUpdateUserRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<UpdateUserRequestDto> userCon = UpdateUserRequestDto.class
                .getDeclaredConstructor(String.class, String.class);
        userCon.setAccessible(true);

        return userCon.newInstance(NICKNAME, RAW_PASSWORD);
    }

    public static CustomUserDetails createMockUserDetails() {
        return CustomUserDetails.of(USER_ID, ROLE_USER);
    }

    public static CustomUserDetails createMockAdminDetails() {
        return CustomUserDetails.of(USER_ID, ROLE_ADMIN);
    }

    public static CustomUserDetails createMockSuperAdminDetails() {
        return CustomUserDetails.of(USER_ID, UserRole.SUPER_ADMIN);
    }
}
