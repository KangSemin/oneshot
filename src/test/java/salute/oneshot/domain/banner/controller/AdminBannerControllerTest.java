package salute.oneshot.domain.banner.controller;

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
import salute.oneshot.domain.banner.dto.request.BannerRequestDto;
import salute.oneshot.domain.banner.dto.response.BannerResponseDto;
import salute.oneshot.domain.banner.dto.service.BannerSDto;
import salute.oneshot.domain.banner.dto.service.UpdateBannerSDto;
import salute.oneshot.domain.banner.entity.Banner;
import salute.oneshot.domain.banner.service.BannerService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.entity.ValidationConst;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.BannerTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminBannerController.class)
@Import(TestSecurityConfig.class)
class AdminBannerControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BannerService bannerService;

    private Banner banner;

    @BeforeEach
    void setUp() {
        banner = BannerTestFactory.createBanner();
    }

    @DisplayName("배너 생성 성공")
    @Test
    void successCreateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createBannerRequestDto();
        BannerResponseDto responseDto =
                BannerResponseDto.from(banner);

        given(bannerService.createBanner(any(BannerSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/admin/banners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.eventId").value(BannerTestFactory.BANNER_ID))
                .andExpect(jsonPath("$.data.imageUrl").value(BannerTestFactory.IMAGE_URL))
                .andExpect(jsonPath("$.data.startTime").value(BannerTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(BannerTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andReturn();
    }

    @DisplayName("배너 생성 실패: 잘못된 형태의 날짜, 시간 입력")
    @Test
    void invalidDateCreateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createInvalidBannerRequestDto();

        given(bannerService.createBanner(any(BannerSDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_DATETIME));

        // when & then
        mockMvc.perform(post("/api/admin/banners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ValidationConst.TIME_TYPE_MESSAGE))

                .andReturn();
    }

    @DisplayName("배너 생성 실패: 존재하지 않는 이벤트 아이디로 배너 생성")
    @Test
    void invalidBannerIdCreateBanner() throws Exception {
        BannerRequestDto requestDto =
                BannerTestFactory.createBannerRequestDto();

        given(bannerService.createBanner(any(BannerSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/admin/banners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EVENT_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배너 수정 성공")
    @Test
    void successUpdateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createBannerRequestDto();
        BannerResponseDto responseDto =
                BannerResponseDto.from(banner);

        given(bannerService.updateBanner(any(UpdateBannerSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/admin/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.eventId").value(BannerTestFactory.BANNER_ID))
                .andExpect(jsonPath("$.data.imageUrl").value(BannerTestFactory.IMAGE_URL))
                .andExpect(jsonPath("$.data.startTime").value(BannerTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(BannerTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andReturn();
    }

    @DisplayName("배너 수정 실패: 존재하지 않는 배너아이디로 수정")
    @Test
    void invalidBannerIdUpdateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createBannerRequestDto();

        given(bannerService.updateBanner(any(UpdateBannerSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.BANNER_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/admin/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.BANNER_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배너 수정 실패: 존재하지 않는 이벤트아이디로 수정")
    @Test
    void invalidEventIdUpdateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createBannerRequestDto();

        given(bannerService.updateBanner(any(UpdateBannerSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/admin/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EVENT_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배너 수정 실패: 잘못된 형태의 날짜, 시간 입력")
    @Test
    void invalidDateUpdateBanner() throws Exception {
        // given
        BannerRequestDto requestDto =
                BannerTestFactory.createInvalidBannerRequestDto();

        given(bannerService.updateBanner(any(UpdateBannerSDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_DATETIME));

        // when & then
        mockMvc.perform(post("/api/admin/banners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ValidationConst.TIME_TYPE_MESSAGE))
                .andReturn();
    }

    @DisplayName("배너 삭제 성공")
    @Test
    void successDeleteBanner() throws Exception {
        // given
        Long bannerId = BannerTestFactory.BANNER_ID;

        // when & then
        mockMvc.perform(delete("/api/admin/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data").value(bannerId))
                .andReturn();
    }

    @DisplayName("배너 삭제 실패: 존재하지 않는 배너아이디로 삭제")
    @Test
    void invalidBannerIdDeleteBanner() throws Exception {
        // given
        Long bannerId = BannerTestFactory.BANNER_ID;

        willThrow(new NotFoundException(ErrorCode.BANNER_NOT_FOUND))
                .given(bannerService).deleteBanner(bannerId);

        // when & then
        mockMvc.perform(delete("/api/admin/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.BANNER_NOT_FOUND.getMessage()))
                .andReturn();
    }
}