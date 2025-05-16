package salute.oneshot.domain.banner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.banner.dto.response.BannerPageResponseDto;
import salute.oneshot.domain.banner.dto.response.BannerResponseDto;
import salute.oneshot.domain.banner.dto.service.GetBannersSDto;
import salute.oneshot.domain.banner.entity.Banner;
import salute.oneshot.domain.banner.service.BannerService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.BannerTestFactory;
import salute.oneshot.util.EventTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BannerController.class)
@Import(TestSecurityConfig.class)
class BannerControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BannerService bannerService;

    private Banner banner;

    @BeforeEach
    void setUp() {
        banner = BannerTestFactory.createBanner();
    }

    @DisplayName("배너 목록 조회 성공(종료시간 내림차순)")
    @Test
    void successGetBanners() throws Exception {
        // given
        Banner banner2 = BannerTestFactory.createBanner2();
        Banner banner3 = BannerTestFactory.createBanner3();

        List<BannerResponseDto> banners = List.of(
                BannerResponseDto.from(banner),
                BannerResponseDto.from(banner2),
                BannerResponseDto.from(banner3));

        PageImpl<BannerResponseDto> bannerPage = new PageImpl<>(banners);

        BannerPageResponseDto responseDto =
                BannerPageResponseDto.from(bannerPage);

        given(bannerService.getBanners(any(GetBannersSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners", BannerTestFactory.BANNER_ID)
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.banners[0].bannerId").value(BannerTestFactory.BANNER_ID))
                .andExpect(jsonPath("$.data.banners[1].bannerId").value(2L))
                .andExpect(jsonPath("$.data.banners[2].bannerId").value(3L))
                .andExpect(jsonPath("$.data.banners[0].eventId").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.banners[1].eventId").value(2L))
                .andExpect(jsonPath("$.data.banners[2].eventId").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banners",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_LIST_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("배너 목록 조회 성공: 시작일 입력")
    @Test
    void successGetBannersWithStartDate() throws Exception {
        // given
        Banner banner2 = BannerTestFactory.createBanner2();
        Banner banner3 = BannerTestFactory.createBanner3();

        List<BannerResponseDto> banners = List.of(
                BannerResponseDto.from(banner2),
                BannerResponseDto.from(banner3));

        PageImpl<BannerResponseDto> bannerPage = new PageImpl<>(banners);

        BannerPageResponseDto responseDto =
                BannerPageResponseDto.from(bannerPage);

        given(bannerService.getBanners(any(GetBannersSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners", BannerTestFactory.BANNER_ID)
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2025-03-10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.banners[0].bannerId").value(2L))
                .andExpect(jsonPath("$.data.banners[1].bannerId").value(3L))
                .andExpect(jsonPath("$.data.banners[0].eventId").value(2L))
                .andExpect(jsonPath("$.data.banners[1].eventId").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banners-with-start-date",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_LIST_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("배너 목록 조회 성공: 종료일 입력")
    @Test
    void successGetBannersWithEndDate() throws Exception {
        // given
        List<BannerResponseDto> banners = List.of(
                BannerResponseDto.from(banner));

        PageImpl<BannerResponseDto> bannerPage = new PageImpl<>(banners);

        BannerPageResponseDto responseDto =
                BannerPageResponseDto.from(bannerPage);

        given(bannerService.getBanners(any(GetBannersSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners", BannerTestFactory.BANNER_ID)
                        .param("page", "1")
                        .param("size", "10")
                        .param("endDate", "2025-03-11")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.banners[0].bannerId").value(BannerTestFactory.BANNER_ID))
                .andExpect(jsonPath("$.data.banners[0].eventId").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banners-with-end-date",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_LIST_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("배너 목록 조회 성공: 시작일 & 종료일 입력")
    @Test
    void successGetBannersWithStartAndEndDate() throws Exception {
        // given
        Banner banner3 = BannerTestFactory.createBanner3();

        List<BannerResponseDto> banners = List.of(
                BannerResponseDto.from(banner3));

        PageImpl<BannerResponseDto> bannerPage = new PageImpl<>(banners);

        BannerPageResponseDto responseDto =
                BannerPageResponseDto.from(bannerPage);

        given(bannerService.getBanners(any(GetBannersSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners", BannerTestFactory.BANNER_ID)
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2025-03-11")
                        .param("endDate", "2025-03-12")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.banners[0].bannerId").value(3L))
                .andExpect(jsonPath("$.data.banners[0].eventId").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banners-with-start-and-end-date",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_LIST_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("배너 목록 조회 성공: 빈 목록 조회")
    @Test
    void successGetBannersWithEmpty() throws Exception {
        // given
        List<BannerResponseDto> emptyBanners = List.of();

        PageImpl<BannerResponseDto> EmptyBannerPage = new PageImpl<>(emptyBanners);

        BannerPageResponseDto responseDto =
                BannerPageResponseDto.from(EmptyBannerPage);

        given(bannerService.getBanners(any(GetBannersSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners", BannerTestFactory.BANNER_ID)
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2025-03-08")
                        .param("endDate", "2025-03-08")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.banners").isArray())
                .andExpect(jsonPath("$.data.banners").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banners-with-empty",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_LIST_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("배너 단건 조회 성공")
    @Test
    void successGetBanner() throws Exception {
        // given
        BannerResponseDto responseDto =
                BannerResponseDto.from(banner);
        Long bannerId = BannerTestFactory.BANNER_ID;

        given(bannerService.getBanner(bannerId))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_BANNER_SUCCESS))
                .andExpect(jsonPath("$.data.bannerId").value(BannerTestFactory.BANNER_ID))
                .andExpect(jsonPath("$.data.eventId").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.imageUrl").value(BannerTestFactory.IMAGE_URL))
                .andExpect(jsonPath("$.data.startTime").value(BannerTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(BannerTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/success-get-banner",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_GET_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_GET_API")))
                .andReturn();
    }

    @DisplayName("배너 단건 조회 실패: 존재하지 않는 배너아이디로 조회")
    @Test
    void invalidBannerIdGetBanner() throws Exception {
        // given
        Long bannerId = BannerTestFactory.BANNER_ID;

        given(bannerService.getBanner(bannerId))
                .willThrow(new NotFoundException(ErrorCode.BANNER_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/banners/{bannerId}", BannerTestFactory.BANNER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.BANNER_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "banner-controller-test/invalid-banner-id-get-banner",
                        ApiDocumentFactory.BANNER_TAG,
                        ApiDocumentationLoader.getSummary("banner", "BANNER_GET_API"),
                        ApiDocumentationLoader.getDescription("banner", "BANNER_GET_API")))
                .andReturn();
    }
}