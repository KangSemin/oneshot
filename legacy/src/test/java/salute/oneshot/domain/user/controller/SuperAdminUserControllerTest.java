package salute.oneshot.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.dto.repuest.UpdateRoleRequestDto;
import salute.oneshot.domain.user.dto.response.UserRoleResponseDto;
import salute.oneshot.domain.user.dto.service.UpdateRoleSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.service.UserService;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperAdminUserController.class)
@Import(TestSecurityConfig.class)
class SuperAdminUserControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserTestFactory.createUser();
    }

    @DisplayName("유저 권한 변경")
    @Test
    void successUpdateUserRole() throws Exception {
        // given
        UpdateRoleRequestDto requestDto =
                UserTestFactory.createUpdateRoleRequestDto();
        UserRoleResponseDto responseDto = UserRoleResponseDto.of(
                user.getId(),
                UserRole.of(requestDto.getUserRole()));

        given(userService.updateUserRole(any(UpdateRoleSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/super-admin/users//{userId}/role", UserTestFactory.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockSuperAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_ROLE_SUCCESS))
                .andExpect(jsonPath("$.data.userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.userRole").value(UserTestFactory.ROLE_ADMIN.toString()))
                .andDo(ApiDocumentFactory.listDoc(
                        "super-admin-user-controller-test/success-update-user-role",
                        ApiDocumentFactory.USER_TAG,
                        ApiDocumentationLoader.getSummary("user", "SUPER_ADMIN_USER_ROLE_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("user", "SUPER_ADMIN_USER_ROLE_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("유저 권한 변경 실패: 같은 권한으로 변경 시도")
    @Test
    void duplicatedUserRoleUpdateUserRole() throws Exception {
        // given
        UpdateRoleRequestDto requestDto =
                UserTestFactory.createUpdateRoleRequestDto();

        given(userService.updateUserRole(any(UpdateRoleSDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_ROLE));

        // when & then
        mockMvc.perform(patch("/api/super-admin/users//{userId}/role", UserTestFactory.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockSuperAdminDetails())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_ROLE.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "super-admin-user-controller-test/duplicated-user-role-update-user-role",
                        ApiDocumentFactory.USER_TAG,
                        ApiDocumentationLoader.getSummary("user", "SUPER_ADMIN_USER_ROLE_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("user", "SUPER_ADMIN_USER_ROLE_UPDATE_API")))
                .andReturn();
    }
}