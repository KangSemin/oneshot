package salute.oneshot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.dto.repuest.UpdateRoleRequestDto;
import salute.oneshot.domain.user.dto.response.UserRoleResponseDto;
import salute.oneshot.domain.user.dto.service.UpdateRoleSDto;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.service.UserService;

@RestController
@RequestMapping("/api/super-admin/users")
@RequiredArgsConstructor
public class SuperAdminUserController {

    private final UserService userService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/{userId}/role")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> updateUserRole(
            @PathVariable Long userId,
            @RequestBody UpdateRoleRequestDto requestDto
    ) {
        UpdateRoleSDto serviceDto =
                UpdateRoleSDto.of(userId, UserRole.of(requestDto.getUserRole()));
        UserRoleResponseDto responseDto =
                userService.updateUserRole(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_ROLE_SUCCESS, responseDto));
    }
}
