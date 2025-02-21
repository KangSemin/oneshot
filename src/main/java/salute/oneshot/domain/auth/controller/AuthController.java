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
import salute.oneshot.global.security.entity.CustomUserDetails;

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

        ResponseCookie refreshCookie = ResponseCookie.from(
                        "refreshToken", tokenInfo.getRefreshToken())
                .httpOnly(true)  // XSS 방어 JavaScript에서 쿠키에 접근하는 것을 차단(프로토콜과 무관)
                .secure(false)   // HTTPS 환경에서만 전송(일단 false)
                .sameSite("Strict")  // CSRF 방어
                .path("/api/auth/refresh")  // 특정 경로에서만 전송
                .maxAge(7 * 24 * 60 * 60)  // 7일간 유효
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
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
        Long userId =
                authService.logOut(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.LOGOUT_SUCCESS, userId));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refreshAccessToken(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        String newAccessToken =
                authService.refreshAccessToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ACS_TOKEN_SUCCESS,
                        newAccessToken));
    }
}
