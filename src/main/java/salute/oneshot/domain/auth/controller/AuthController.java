package salute.oneshot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.auth.dto.request.SignInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.AccessTokenDto;
import salute.oneshot.domain.auth.dto.response.AuthResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.SignInSDto;
import salute.oneshot.domain.auth.dto.service.AuthSDto;
import salute.oneshot.domain.auth.dto.service.SignOutSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponse<AuthResponseDto>> signUp(
            @Valid @RequestBody SignUpRequestDto requestDto
    ) {
        AuthSDto signUpSDto = AuthSDto.of(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickName());

        AuthResponseDto serviceDto =
                authService.userSignUp(signUpSDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.SIGNUP_SUCCESS, serviceDto));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<ApiResponse<AccessTokenDto>> signIn(
            @Valid @RequestBody SignInRequestDto requestDto
    ) {
        SignInSDto signInSDto = SignInSDto.of(
                requestDto.getEmail(), requestDto.getPassword());

        TokenInfo tokenInfo =
                authService.userSignIn(signInSDto);

        ResponseCookie refreshCookie =
                ResponseCookie.from("refreshToken", tokenInfo.getRefreshToken())
                        .httpOnly(true)  // XSS 방어 JavaScript에서 쿠키에 접근하는 것을 차단(프로토콜과 무관)
                        .secure(false)    // HTTPS 환경에서만 전송
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

    @PostMapping("/signout")
    public ResponseEntity<ApiResponse<AuthResponseDto>> signOut(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestHeader("Authorization") String token
    ) {
        SignOutSDto serviceDto =
                SignOutSDto.of(userDetails.getId(), token);

        AuthResponseDto responseDto = authService.signOut(serviceDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.LOGOUT_SUCCESS, responseDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<String>> reissueToken(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        String newAccessToken =
                authService.reissueAccessToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ACS_TOKEN_SUCCESS,
                        newAccessToken));
    }
}
