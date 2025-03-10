package salute.oneshot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.dto.repuest.UpdateUserRequestDto;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.DeleteUserSDto;
import salute.oneshot.domain.user.dto.service.UpdateUserSDto;
import salute.oneshot.domain.user.service.UserService;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserResponseDto responseDto =
                userService.getUserInfo(userDetails.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_USER_SUCCESS, responseDto));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUserInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UpdateUserRequestDto requestDto
    ) {
        UpdateUserSDto serviceDto = UpdateUserSDto.of(
                userDetails.getId(),
                requestDto.getNickname(),
                requestDto.getPassword()
        );
        UserResponseDto responseDto =
                userService.updateUserInfo(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_USER_SUCCESS, responseDto));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Long>> deleteUser(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestHeader("Authorization") String token
    ) {
        DeleteUserSDto serviceDto =
                DeleteUserSDto.of(userDetails.getId(), token);

        Long userId =
                userService.deleteUser(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_USER_SUCCESS, userId));
    }
}
