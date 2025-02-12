package salute.oneshot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.service.UserService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserResponseDto userResponseDto = userService.getUserInfo(userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(ApiResponseMessage.GET_USER_SUCCESS, userResponseDto));
    }
}
