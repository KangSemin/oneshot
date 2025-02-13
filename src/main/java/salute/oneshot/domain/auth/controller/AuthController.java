package salute.oneshot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.auth.dto.request.SignInRequestDto;
import salute.oneshot.domain.auth.dto.request.SignUpRequestDto;
import salute.oneshot.domain.auth.dto.response.SignInResponseDto;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.service.SignInSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.service.AuthService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> userSignUp(
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

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<SignInResponseDto>> userSignIn(
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
}
