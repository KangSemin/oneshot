package salute.oneshot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseMessage;
import salute.oneshot.domain.user.dto.repuest.UserUpdateRequestDto;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.UserUpdateSDto;
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
        UserResponseDto userResponseDto =
                userService.getUserInfo(userDetails.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseMessage.GET_USER_SUCCESS,
                        userResponseDto
                ));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUserInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        UserUpdateSDto userUpdateSDto = UserUpdateSDto.of(
                userDetails.getId(),
                userUpdateRequestDto.getNickName(),
                userUpdateRequestDto.getPassword()
        );
        UserResponseDto userResponseDto =
                userService.updateUserInfo(userUpdateSDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseMessage.UPDATE_USER_SUCCESS,
                        userResponseDto
                ));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> deleteUser(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserResponseDto userResponseDto =
                userService.deleteUser(userDetails.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseMessage.DELETE_USER_SUCCESS,
                        userResponseDto
                ));
    }
}
