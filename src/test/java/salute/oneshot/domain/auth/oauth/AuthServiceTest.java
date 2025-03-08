package salute.oneshot.domain.auth.oauth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.repository.RedisBlacklistRepository;
import salute.oneshot.domain.auth.repository.RedisRefreshTokenRepository;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.TokenTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RedisRefreshTokenRepository refreshTokenRepository;
    @Mock
    private RedisBlacklistRepository blacklistRepository;

    @DisplayName("회원가입 성공")
    @Test
    void successUserSignUp() {
        // given
        SignUpSDto serviceDto = UserTestFactory.createSignUpSDto();

        given(userRepository.existsByEmail(serviceDto.getEmail()))
                .willReturn(false);
        given(passwordEncoder.encode(serviceDto.getPassword()))
                .willReturn(UserTestFactory.ENCODED_PASSWORD);

        User user = UserTestFactory.createUser();
        given(userRepository.save(Mockito.any(User.class)))
                .willReturn(user);

        // when
        SignUpResponseDto responseDto = authService.userSignUp(serviceDto);

        // then
        assertNotNull(responseDto);
        assertThat(UserTestFactory.EMAIL).isEqualTo(responseDto.getEmail());
        assertThat(UserTestFactory.NICKNAME).isEqualTo(responseDto.getNickname());

        then(userRepository).should().existsByEmail(serviceDto.getEmail());
        then(passwordEncoder).should().encode(serviceDto.getPassword());
        then(userRepository).should().save(Mockito.any(User.class));
    }

    @DisplayName("회원가입 실패: 중복메일로 가입시도")
    @Test
    void duplicatedEmailSignUp() {
        // given
        SignUpSDto sDto = UserTestFactory.createSignUpSDto();

        given(userRepository.existsByEmail(sDto.getEmail())).willReturn(true);
        // when & then
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            authService.userSignUp(sDto);
        });

        assertThat(ErrorCode.DUPLICATE_EMAIL).isEqualTo(exception.getErrorCode());

        then(userRepository).should().existsByEmail(sDto.getEmail());
        then(userRepository).shouldHaveNoMoreInteractions();
        then(passwordEncoder).shouldHaveNoMoreInteractions();
    }

    @DisplayName("로그인 성공")
    @Test
    void successLogIn() {
        // given
        LogInSDto sDto = UserTestFactory.createLogInSDto();
        User user = UserTestFactory.createUser();
        TokenInfo toKenInfo = TokenTestFactory.createTokenInfo();

        given(userRepository.findByEmailAndIsDeletedIsFalse(sDto.getEmail()))
                .willReturn(Optional.of(user));
        given(passwordEncoder.matches(sDto.getPassword(), user.getPassword()))
                .willReturn(true);
        given(jwtProvider.createToken(user.getId(), user.getUserRole()))
                .willReturn(toKenInfo);

        // when
        TokenInfo result = authService.logIn(sDto);

        // then
        assertThat(result).isEqualTo(toKenInfo);

        then(userRepository).should().findByEmailAndIsDeletedIsFalse(sDto.getEmail());
        then(passwordEncoder).should().matches(sDto.getPassword(), user.getPassword());
        then(jwtProvider).should().createToken(user.getId(), user.getUserRole());
        then(refreshTokenRepository).should().save(user.getId(), user.getUserRole(), toKenInfo.getRefreshToken());
    }

    @DisplayName("로그인 실패: 존재하지 않는 메일로 로그인")
    @Test
    void notFoundEmailLogin() {
        // given
        LogInSDto sDto = UserTestFactory.createLogInSDto();
        given(userRepository.findByEmailAndIsDeletedIsFalse(sDto.getEmail()))
                .willReturn(Optional.empty());
        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                authService.logIn(sDto));

        assertThat(ErrorCode.USER_NOT_FOUND).isEqualTo(exception.getErrorCode());

        then(userRepository).should().findByEmailAndIsDeletedIsFalse(sDto.getEmail());
        then(userRepository).shouldHaveNoMoreInteractions();
        then(passwordEncoder).shouldHaveNoInteractions();
        then(jwtProvider).shouldHaveNoInteractions();
        then(refreshTokenRepository).shouldHaveNoInteractions();
    }

    @DisplayName("로그인 실패: 비밀번호 불일치")
    @Test
    void invalidPasswordLogIn() {
        // given
        LogInSDto sDto = UserTestFactory.createLogInSDto();
        User user = UserTestFactory.createUser();
        given(userRepository.findByEmailAndIsDeletedIsFalse(sDto.getEmail()))
                .willReturn(Optional.of(user));
        given(passwordEncoder.matches(sDto.getPassword(), user.getPassword()))
                .willReturn(false);

        // when & then
        InvalidException exception = assertThrows(InvalidException.class, () ->
                authService.logIn(sDto));

        assertThat(ErrorCode.LOGIN_FAILED).isEqualTo(exception.getErrorCode());

        then(userRepository).should().findByEmailAndIsDeletedIsFalse(sDto.getEmail());
        then(userRepository).shouldHaveNoMoreInteractions();
        then(passwordEncoder).should().matches(sDto.getPassword(), user.getPassword());
        then(jwtProvider).shouldHaveNoInteractions();
        then(refreshTokenRepository).shouldHaveNoInteractions();
    }

    @DisplayName("로그아웃 성공")
    @Test
    void successLogOut() {
        // given
        LogOutSDto sDto = UserTestFactory.createLogOutSDto();
        long remainMilliSeconds = TokenTestFactory.TOKEN_REMAIN_TIME;
        String token = TokenTestFactory.ACCESS_TOKEN;

        given(jwtProvider.extractToken(sDto.getToken()))
                .willReturn(token);
        given(jwtProvider.getRemainMilliSeconds(token))
                .willReturn(remainMilliSeconds);

        // when
        authService.logOut(sDto);

        // then
        then(jwtProvider).should().getRemainMilliSeconds(token);
        then(jwtProvider).shouldHaveNoMoreInteractions();
        then(blacklistRepository).should().save(token, remainMilliSeconds);
    }

    @DisplayName("리프레시토큰으로 어세스토큰 재발급 성공")
    @Test
    void SuccessRefreshAccessToken() {
        // given
        String refreshToken = TokenTestFactory.REFRESH_TOKEN;
        Long userId = UserTestFactory.USER_ID;
        UserRole userRole = UserTestFactory.ROLE_USER;
        TokenInfo tokenInfo = TokenTestFactory.createTokenInfo();

        given(jwtProvider.getUserIdFromToken(refreshToken))
                .willReturn(userId);
        given(refreshTokenRepository.findRoleById(userId))
                .willReturn(userRole);
        given(jwtProvider.createToken(userId, userRole))
                .willReturn(tokenInfo);
        given(refreshTokenRepository.validate(userId, refreshToken))
                .willReturn(true);

        // when
        TokenInfo result = authService.refreshAccessToken(refreshToken);

        // then
        assertThat(result).isEqualTo(tokenInfo);

        then(jwtProvider).should().getUserIdFromToken(refreshToken);
        then(jwtProvider).should().createToken(userId, userRole);
        then(jwtProvider).shouldHaveNoMoreInteractions();
        then(refreshTokenRepository).should().validate(userId, refreshToken);
        then(refreshTokenRepository).should().findRoleById(userId);
        then(refreshTokenRepository).should().save(
                userId,
                userRole,
                tokenInfo.getRefreshToken());
        then(refreshTokenRepository).shouldHaveNoMoreInteractions();
    }

    @DisplayName("리프레시토큰으로 어세스토큰 재발급 실패: 유저아이디와 리프레시토큰 불일치")
    @Test
    void invalidUserIdAndRefreshToken() {
        // given
        String refreshToken = TokenTestFactory.REFRESH_TOKEN;
        Long userId = UserTestFactory.USER_ID;
        given(jwtProvider.getUserIdFromToken(refreshToken))
                .willReturn(userId);
        given(refreshTokenRepository.validate(userId, refreshToken))
                .willReturn(false);

        // when & then
        InvalidException exception = assertThrows(InvalidException.class, () ->
                authService.refreshAccessToken(refreshToken));

        assertThat(ErrorCode.INVALID_TOKEN).isEqualTo(exception.getErrorCode());

        then(jwtProvider).should().getUserIdFromToken(refreshToken);
        then(refreshTokenRepository).should().validate(userId, refreshToken);
        then(jwtProvider).shouldHaveNoMoreInteractions();;
        then(refreshTokenRepository).shouldHaveNoMoreInteractions();
    }
}