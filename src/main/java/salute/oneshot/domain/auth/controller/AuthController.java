package salute.oneshot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.auth.dto.request.SignInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.SignInResponseDto;
import salute.oneshot.domain.auth.dto.response.AuthResponseDto;
import salute.oneshot.domain.auth.dto.service.SignInSDto;
import salute.oneshot.domain.auth.dto.service.AuthSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
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
    public ResponseEntity<ApiResponse<SignInResponseDto>> signIn(
            @Valid @RequestBody SignInRequestDto requestDto
    ) {
        SignInSDto signInSDto = SignInSDto.of(
                requestDto.getEmail(), requestDto.getPassword());

        SignInResponseDto signInResponseDto =
                authService.userSignIn(signInSDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.LOGIN_SUCCESS, signInResponseDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<ApiResponse<AuthResponseDto>> signOut(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        AuthResponseDto responseDto = authService.signOut(userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.LOGOUT_SUCCESS, responseDto));
    }
}
