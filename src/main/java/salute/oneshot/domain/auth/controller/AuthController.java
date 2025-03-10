package salute.oneshot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.auth.dto.request.LogInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.AccessTokenDto;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> signUp(
            @Valid @RequestBody SignUpRequestDto requestDto
    ) {
        SignUpSDto signUpSDto = SignUpSDto.of(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickName());
        SignUpResponseDto serviceDto =
                authService.userSignUp(signUpSDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.SIGNUP_SUCCESS, serviceDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AccessTokenDto>> logIn(
            @Valid @RequestBody LogInRequestDto requestDto
    ) {
        LogInSDto logInSDto = LogInSDto.of(
                requestDto.getEmail(), requestDto.getPassword());
        TokenInfo tokenInfo =
                authService.logIn(logInSDto);

        ResponseCookie refreshCookie =
                createRefreshTokenCookie(tokenInfo.getRefreshToken());
        ResponseCookie accessCookie =
                createAccessTokenCookie(tokenInfo.getAccessToken());

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                .body(ApiResponse.success(
                        ApiResponseConst.LOGIN_SUCCESS,
                        AccessTokenDto.from(tokenInfo)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Long>> logOut(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestHeader("Authorization") String token
    ) {
        LogOutSDto serviceDto =
                LogOutSDto.of(userDetails.getId(), token);
        authService.logOut(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.LOGOUT_SUCCESS, userDetails.getId()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AccessTokenDto>> refreshAccessToken(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        TokenInfo tokenInfo =
                authService.refreshAccessToken(refreshToken);
        ResponseCookie refreshCookie =
                createRefreshTokenCookie(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ACS_TOKEN_SUCCESS,
                        AccessTokenDto.from(tokenInfo)));
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)  // XSS 방어 JavaScript에서 쿠키에 접근하는 것을 차단(프로토콜과 무관)
                .secure(false)   // HTTPS 환경에서만 전송(일단 false)
                .sameSite("Lax")  // CSRF 방어
                .path("/")  // 특정 경로에서만 전송
                .maxAge(7 * 24 * 60 * 60)  // 7일간 유효
                .build();
    }

    public static ResponseCookie createAccessTokenCookie(String accessToken) {
        return ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)  // XSS 방어 JavaScript에서 쿠키에 접근하는 것을 차단(프로토콜과 무관)
                .secure(false)   // HTTPS 환경에서만 전송(일단 false)
                .sameSite("Lax")  // CSRF 방어
                .path("/")  // 특정 경로에서만 전송
                .maxAge(60 * 60)  // 1시간 유효
                .build();
    }
}
