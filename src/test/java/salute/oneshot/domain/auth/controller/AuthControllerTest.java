package salute.oneshot.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.apache.hc.core5.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import salute.oneshot.domain.auth.dto.request.LogInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.global.security.model.CustomUserDetails;
import salute.oneshot.global.util.NonceGenerator;
import salute.oneshot.util.TokenTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private NonceGenerator nonceGenerator;

    @MockitoBean
    private JwtProvider jwtProvider;

    @MockitoBean
    private JwtFilter jwtFilter;

    private User user;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();

        user = UserTestFactory.createUser();

    }

    @DisplayName("회원가입 성공")
    @Test
    void successSignUp() throws Exception {
        // given
        SignUpRequestDto requestDto =
                UserTestFactory.createSignUpRequestDto();
        SignUpResponseDto responseDto =
                UserTestFactory.createSignUpResponseDto(user);

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
                .andDo(print())
                .andReturn();
    }

    @DisplayName("회원가입 실패: 중복 이메일")
    @Test
    void duplicatedEmailSignUp() throws Exception {
        // given
        SignUpRequestDto requestDto =
                UserTestFactory.createSignUpRequestDto();
        SignUpSDto serviceDto =
                UserTestFactory.createSignUpSDto(
                        requestDto.getEmail(),
                        requestDto.getPassword(),
                        requestDto.getPassword());

        // 디버그 로그 추가
        System.out.println("RequestDto: " + requestDto);
        System.out.println("ServiceDto: " + serviceDto);

        given(authService.userSignUp(any(SignUpSDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_EMAIL));

        // when & then
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_EMAIL.getMessage()));
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
                .andDo(print())
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
        TokenInfo tokenInfo =
                TokenTestFactory.createTokenInfo();

        given(authService.logIn(any(LogInSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.USER_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.USER_NOT_FOUND.getMessage()));
    }

    @DisplayName("로그인 실패: 비밀번호 불일치")
    @Test
    void invalidPasswordLogIn() throws Exception {
        // given
        LogInRequestDto requestDto =
                UserTestFactory.createLogInRequestDto();
        TokenInfo tokenInfo =
                TokenTestFactory.createTokenInfo();

        given(authService.logIn(any(LogInSDto.class)))
                .willThrow(new InvalidException(ErrorCode.LOGIN_FAILED));

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.LOGIN_FAILED.getMessage()));
    }

    @DisplayName("로그아웃 성공")
    @Test
    void successLogOut() throws Exception {
        // given
        Long userId = UserTestFactory.USER_ID;
        String token = TokenTestFactory.ACCESS_TOKEN;

        CustomUserDetails userDetails = CustomUserDetails.of(
                userId,
                UserTestFactory.ROLE_USER
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        willDoNothing().given(authService).logOut(any(LogOutSDto.class));

        // when & then
        mockMvc.perform(post("/api/auth/logout")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.LOGOUT_SUCCESS))
                .andExpect(jsonPath("$.data").value(userId))
                .andDo(print());
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
                .andDo(print());
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
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_TOKEN.getMessage()));
    }
}