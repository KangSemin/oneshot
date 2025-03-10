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
import org.springframework.test.web.servlet.MockMvc;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.user.dto.repuest.UpdateUserRequestDto;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.DeleteUserSDto;
import salute.oneshot.domain.user.dto.service.UpdateUserSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.service.UserService;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserTestFactory.createUser();
    }

    @DisplayName("유저 정보 조회 성공")
    @Test
    void successGetUserInfo() throws Exception {
        // given
        UserResponseDto responseDto =
                UserResponseDto.from(user);

        given(userService.getUserInfo(user.getId()))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/users")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_USER_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.nickname").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.userRole").value(UserTestFactory.ROLE_USER.toString()))
                .andReturn();
    }

    @DisplayName("유저 정보 조회 실패: 존재하지 않거나 소프르딜리트 된 유저아이디")
    @Test
    void invalidUserIdGetUserInfo() throws Exception {
        // given
        given(userService.getUserInfo(user.getId()))
                .willThrow(new NotFoundException(ErrorCode.USER_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/users")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.USER_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("유저 정보 수정 성공")
    @Test
    void successUpdateUserInfo() throws Exception {
        // given
        UpdateUserRequestDto requestDto =
                UserTestFactory.createUpdateUserRequestDto();
        UserResponseDto responseDto =
                UserResponseDto.from(user);

        given(userService.updateUserInfo(any(UpdateUserSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_USER_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.nickname").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.userRole").value(UserTestFactory.ROLE_USER.toString()))
                .andReturn();
    }

    @DisplayName("유저 정보 수정 실패: 존재하지 않거나 소프르딜리트 된 유저아이디성공")
    @Test
    void invalidUserIdUpdateUserInfo() throws Exception {
        // given
        UpdateUserRequestDto requestDto =
                UserTestFactory.createUpdateUserRequestDto();

        given(userService.updateUserInfo(any(UpdateUserSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.USER_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.USER_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("유저 소프트 딜리트 성공")
    @Test
    void successDeleteUser() throws Exception {
        // given
        Long userId = UserTestFactory.USER_ID;

        given(userService.deleteUser(any(DeleteUserSDto.class)))
                .willReturn(userId);

        // when & then
        mockMvc.perform(delete("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_USER_SUCCESS))
                .andExpect(jsonPath("$.data").value(userId))
                .andReturn();
    }

    @DisplayName("유저 소프트 딜리트 실패: 존재하지 않거나 소프르딜리트 된 유저아이디")
    @Test
    void invalidUserIdDeleteUser() throws Exception {
        // given
        given(userService.deleteUser(any(DeleteUserSDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_USER_DELETE));

        // when & then
        mockMvc.perform(delete("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .header("Authorization", "Bearer test-token"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_USER_DELETE.getMessage()))
                .andReturn();
    }
}