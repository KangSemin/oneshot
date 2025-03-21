package salute.oneshot.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.apache.hc.core5.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MvcResult;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.auth.dto.request.LogInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.TokenTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
@Import(TestSecurityConfig.class)
class AuthControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserTestFactory.createUser();
    }

    @DisplayName("회원가입 성공")
    @Test
    void successSignUp() throws Exception {
        // given
        SignUpRequestDto requestDto =
                UserTestFactory.createSignUpRequestDto();
        SignUpResponseDto responseDto =
                SignUpResponseDto.from(user);

        given(authService.userSignUp(any(SignUpSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.SIGNUP_SUCCESS))
                .andExpect(jsonPath("$.data.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.nickname").value(UserTestFactory.NICKNAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/success-sign-up",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_SIGNUP_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_SIGNUP_API")))
                .andReturn();
    }

    @DisplayName("회원가입 실패: 중복 이메일")
    @Test
    void duplicatedEmailSignUp() throws Exception {
        // given
        SignUpRequestDto requestDto =
                UserTestFactory.createSignUpRequestDto();

        given(authService.userSignUp(any(SignUpSDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_EMAIL));

        // when & then
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_EMAIL.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/duplicated-email-sign-up",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_SIGNUP_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_SIGNUP_API")))
                .andReturn();
    }

    @DisplayName("로그인 성공")
    @Test
    void successLogIn() throws Exception {
        // given
        LogInRequestDto requestDto =
                UserTestFactory.createLogInRequestDto();
        TokenInfo tokenInfo =
                TokenTestFactory.createTokenInfo();

        given(authService.logIn(any(LogInSDto.class)))
                .willReturn(tokenInfo);

        // when & then
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(header().exists("Set-Cookie"))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.LOGIN_SUCCESS))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/success-log-in",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_LOGIN_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_LOGIN_API")))
                .andReturn();

        // 수동으로 Set-Cookie 헤더 검증
        List<String> setCookieHeaders = result.getResponse().getHeaders("Set-Cookie");
        assertTrue("Set-Cookie 헤더에 refreshToken 없음",
                setCookieHeaders.stream().anyMatch(header -> header.contains("refreshToken")));
        assertTrue("Set-Cookie 헤더에 accessToken 없음",
                setCookieHeaders.stream().anyMatch(header -> header.contains("accessToken")));
    }

    @DisplayName("로그인 실패: 존재하지 않는 메일로 로그인")
    @Test
    void invalidEmailLogIn() throws Exception {
        // given
        LogInRequestDto requestDto =
                UserTestFactory.createLogInRequestDto();

        given(authService.logIn(any(LogInSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.USER_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.USER_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/invalid-email-log-in",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_LOGIN_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_LOGIN_API")))
                .andReturn();
    }

    @DisplayName("로그인 실패: 비밀번호 불일치")
    @Test
    void invalidPasswordLogIn() throws Exception {
        // given
        LogInRequestDto requestDto =
                UserTestFactory.createLogInRequestDto();

        given(authService.logIn(any(LogInSDto.class)))
                .willThrow(new InvalidException(ErrorCode.LOGIN_FAILED));

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.LOGIN_FAILED.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/invalid-password-log-in",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_LOGIN_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_LOGIN_API")))
                .andReturn();
    }

    @DisplayName("로그아웃 성공")
    @Test
    void successLogOut() throws Exception {
        // given
        Long userId = UserTestFactory.USER_ID;
        String token = TokenTestFactory.ACCESS_TOKEN;

        // when & then
        mockMvc.perform(post("/api/auth/logout")
                        .header("Authorization", token)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.LOGOUT_SUCCESS))
                .andExpect(jsonPath("$.data").value(userId))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/success-log-out",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_LOGOUT_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_LOGOUT_API")))
                .andReturn();
    }

    @DisplayName("리프레시 토큰으로 엑세스 토큰 재발급 성공")
    @Test
    void successRefreshToken() throws Exception {
        // given
        String refreshToken =
                TokenTestFactory.REFRESH_TOKEN;
        TokenInfo tokenInfo =
                TokenTestFactory.createTokenInfo();

        given(authService.refreshAccessToken(refreshToken))
                .willReturn(tokenInfo);

        // when & then
        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(new Cookie("refreshToken", refreshToken)))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.SET_COOKIE))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ACS_TOKEN_SUCCESS))
                .andExpect(jsonPath("$.data.accessToken").exists())
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/success-refresh-token",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_REFRESH_TOKEN_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_REFRESH_TOKEN_API")))
                .andReturn();
    }

    @DisplayName("리프레시 토큰으로 엑세스 토큰 재발급 실패: 유저아이디와 리프레시토큰 불일치")
    @Test
    void mismatchInfoFailRefreshToken() throws Exception {
        // given
        String refreshToken =
                TokenTestFactory.REFRESH_TOKEN;

        given(authService.refreshAccessToken(refreshToken))
                .willThrow(new InvalidException(ErrorCode.INVALID_TOKEN));

        // when & then
        mockMvc.perform(post("/api/auth/refresh")
                        .cookie(new Cookie("refreshToken", refreshToken)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_TOKEN.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "auth-controller-test/mismatch-info-fail-refresh-token",
                        ApiDocumentFactory.AUTH_TAG,
                        ApiDocumentationLoader.getSummary("auth", "AUTH_REFRESH_TOKEN_API"),
                        ApiDocumentationLoader.getDescription("auth", "AUTH_REFRESH_TOKEN_API")))
                .andReturn();
    }
}