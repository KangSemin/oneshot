package salute.oneshot.domain.coupon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.response.*;
import salute.oneshot.domain.coupon.dto.service.GetCpnSDto;
import salute.oneshot.domain.coupon.dto.service.GetUserCpnSDto;
import salute.oneshot.domain.coupon.dto.service.UserCpnSDto;
import salute.oneshot.domain.coupon.entity.Coupon;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.AddressTestFactory;
import salute.oneshot.util.CouponTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CouponController.class)
@Import(TestSecurityConfig.class)
class CouponControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CouponService couponService;

    private Coupon coupon;
    private User user;
    private UserCoupon userCoupon;
    private UserCoupon userCoupon2;

    @BeforeEach
    void setUp() {
        coupon = CouponTestFactory.createCoupon();
        user = UserTestFactory.createUser();
        userCoupon = UserCoupon.of(user, coupon);
        ReflectionTestUtils.setField(userCoupon,"id",1L);
        userCoupon2 = UserCoupon.of(user, coupon);
        ReflectionTestUtils.setField(userCoupon2,"status", UserCouponStatus.USED);
    }

    @DisplayName("유저쿠폰 사용 성공")
    @Test
    void successUseUserCoupon() throws Exception {
        // given
        UserCpnDetailResponseDto responseDto =
                UserCpnDetailResponseDto.of(userCoupon);

        given(couponService.useUserCoupon(any(UserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/coupons/users/{userCouponId}", CouponTestFactory.USER_COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.USE_USER_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.userCouponId").value(1L))
                .andExpect(jsonPath("$.data.status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-use-user-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_USE_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_USE_API")))
                .andReturn();
    }

    @DisplayName("유저쿠폰 사용 실패: 존재하지 않는 유저 쿠폰 아이디 또는 사용 혹은 만료된 쿠폰으로 시도")
    @Test
    void invalidUserCouponIdUseUserCoupon() throws Exception {
        // given
        given(couponService.useUserCoupon(any(UserCpnSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/coupons/users/{userCouponId}", CouponTestFactory.USER_COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/invalid-user-coupon-id-use-user-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_USE_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_USE_API")))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공")
    @Test
    void successGetCoupons() throws Exception {
        // given
        Coupon coupon2 = CouponTestFactory.createCoupon2();
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon),
                CpnBriefResponseDto.from(coupon2),
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCoupons(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.coupons[1].id").value(2L))
                .andExpect(jsonPath("$.data.coupons[1].couponName").value("2000원 할인쿠폰"))
                .andExpect(jsonPath("$.data.coupons[2].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[2].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공: 시작일 입력")
    @Test
    void successGetCouponsWithStartDate() throws Exception {
        // given
        Coupon coupon2 = CouponTestFactory.createCoupon2();
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon2),
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCoupons(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons")
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2023-03-02")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(2L))
                .andExpect(jsonPath("$.data.coupons[1].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value("2000원 할인쿠폰"))
                .andExpect(jsonPath("$.data.coupons[1].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-with-start-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공: 종료일 입력")
    @Test
    void successGetCouponsWithEndDate() throws Exception {
        // given
        Coupon coupon2 = CouponTestFactory.createCoupon2();
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon),
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCoupons(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons")
                        .param("page", "1")
                        .param("size", "10")
                        .param("endDate", "2023-03-10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.coupons[1].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.coupons[1].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-with-end-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공: 시작일 & 종료일 입력")
    @Test
    void successGetCouponsWithStartAndEndDate() throws Exception {
        // given
        Coupon coupon2 = CouponTestFactory.createCoupon2();
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon2));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCoupons(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons")
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2023-03-05")
                        .param("endDate", "2023-03-11")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(2L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value("2000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-with-start-and-end-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공: 빈 목록 조회")
    @Test
    void successGetEmptyCoupons() throws Exception {
        // given
        List<CpnBriefResponseDto> emptyCoupons = List.of();
        PageImpl<CpnBriefResponseDto> emptyPage = new PageImpl<>(emptyCoupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(emptyPage);

        given(couponService.getCoupons(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons").isArray())
                .andExpect(jsonPath("$.data.coupons").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-empty-coupons",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.START_DATE_PARAM,
                        ApiDocumentFactory.END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("쿠폰 단건 조회 성공")
    @Test
    void successGetCoupon() throws Exception {
        // given
        CpnDetailResponseDto responseDto =
                CpnDetailResponseDto.from(coupon);

        given(couponService.getCoupon(CouponTestFactory.COUPON_ID))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/{couponId}", CouponTestFactory.COUPON_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.discountValue").value(CouponTestFactory.DISCOUNT_VALUE))
                .andExpect(jsonPath("$.data.startTime").value(CouponTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(CouponTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                                ApiDocumentationLoader.getSummary("coupon", "COUPON_GET_API"),
                                ApiDocumentationLoader.getDescription("coupon", "COUPON_GET_API")))
                .andReturn();
    }

    @DisplayName("쿠폰 단건 조회 실패: 존재하지 않는 쿠폰 아이디로 조회 시도")
    @Test
    void invalidCouponIdGetCoupon() throws Exception {
        // given
        given(couponService.getCoupon(CouponTestFactory.COUPON_ID))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/coupons/{couponId}", CouponTestFactory.COUPON_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/invalid-coupon-id-get-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "COUPON_GET_API"),
                        ApiDocumentationLoader.getDescription("coupon", "COUPON_GET_API")))
                .andReturn();
    }

    @DisplayName("유저쿠폰 목록 조회 성공")
    @Test
    void successGetUserCoupons() throws Exception {
        // given
        List<UserCpnBriefResponseDto> userCoupons = List.of(UserCpnBriefResponseDto.from(userCoupon));
        PageImpl<UserCpnBriefResponseDto> page = new PageImpl<>(userCoupons);
        UserCpnPageResponseDto responseDto = UserCpnPageResponseDto.from(page);

        given(couponService.getUserCoupons(any(GetUserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/users")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.userCoupons[0].userCouponId").value(1L))
                .andExpect(jsonPath("$.data.userCoupons[0].userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.userCoupons[0].status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.userCoupons[0].coupon.id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.userCoupons[0].coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-user-coupons",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("유저쿠폰 목록 조회 성공: 빈 목록 조회")
    @Test
    void successGetEmptyUserCoupons() throws Exception {
        // given
        List<UserCpnBriefResponseDto> emptyUserCoupons = List.of();
        PageImpl<UserCpnBriefResponseDto> emptyPage = new PageImpl<>(emptyUserCoupons);
        UserCpnPageResponseDto responseDto = UserCpnPageResponseDto.from(emptyPage);

        given(couponService.getUserCoupons(any(GetUserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/users")
                        .param("page", "1")
                        .param("size", "10")
                        .param("status", "ISSUED")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.userCoupons").isArray())
                .andExpect(jsonPath("$.data.userCoupons").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-empty-user-coupons",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentFactory.COUPON_STATUS_PARAM,
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }


    @DisplayName("유저쿠폰 목록 조회 성공: ISSUED 상태 조회")
    @Test
    void successGetUserCouponsWithISSUED() throws Exception {
        // given
        List<UserCpnBriefResponseDto> userCoupons = List.of(UserCpnBriefResponseDto.from(userCoupon));
        PageImpl<UserCpnBriefResponseDto> page = new PageImpl<>(userCoupons);
        UserCpnPageResponseDto responseDto = UserCpnPageResponseDto.from(page);
        given(couponService.getUserCoupons(any(GetUserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/users")
                        .param("page", "1")
                        .param("size", "10")
                        .param("status", "ISSUED")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.userCoupons[0].userCouponId").value(1L))
                .andExpect(jsonPath("$.data.userCoupons[0].userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.userCoupons[0].status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.userCoupons[0].coupon.id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.userCoupons[0].coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-user-coupons-with-issued",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "USER_COUPON_LIST_API"),
                        ApiDocumentFactory.COUPON_STATUS_PARAM,
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("유저쿠폰 단건 조회 성공")
    @Test
    void successGetUserCoupon() throws Exception {
        // given
        UserCpnDetailResponseDto responseDto =
                UserCpnDetailResponseDto.of(userCoupon);

        given(couponService.getUserCoupon(any(UserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/users/{userCouponId}", CouponTestFactory.USER_COUPON_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.userCouponId").value(1L))
                .andExpect(jsonPath("$.data.status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon/success-get-user-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                                ApiDocumentationLoader.getSummary("coupon", "USER_COUPON_GET_API"),
                                ApiDocumentationLoader.getDescription("coupon", "USER_COUPON_GET_API")))
                .andReturn();
    }

    @DisplayName("유저쿠폰 단건 조회 실패: 존재하지 않는 유저쿠폰 아이디로 조회 시도")
    @Test
    void invalidUserCouponIdGetUserCoupon() throws Exception {
        // given
        given(couponService.getUserCoupon(any(UserCpnSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/coupons/users/{userCouponId}", CouponTestFactory.USER_COUPON_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/invalid-user-coupon-id-get-user-coupon",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "USER_COUPON_GET_API"),
                        ApiDocumentationLoader.getDescription("coupon", "USER_COUPON_GET_API")))
                .andReturn();
    }

    @DisplayName("이벤트용 쿠폰 목록 조회 성공")
    @Test
    void successGetCouponsForEvent() throws Exception {
        // given
        Coupon coupon2 = CouponTestFactory.createCoupon2();
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon),
                CpnBriefResponseDto.from(coupon2),
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCouponsForEvent(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/for-event")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.coupons[1].id").value(2L))
                .andExpect(jsonPath("$.data.coupons[2].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.coupons[1].couponName").value("2000원 할인쿠폰"))
                .andExpect(jsonPath("$.data.coupons[2].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-for-event",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("이벤트용 쿠폰 목록 조회 성공: 시작일 입력(쿠폰 시작일 < 이벤트 시작일)")
    @Test
    void successGetCouponsForEventWithEventStartDate() throws Exception {
        // given
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon),
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCouponsForEvent(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/for-event")
                        .param("page", "1")
                        .param("size", "10")
                        .param("eventStartDate", "2025-03-02")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.coupons[1].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.coupons[1].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-for-event-with-event-start-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.EVENT_START_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("이벤트용 쿠폰 목록 조회 성공: 종료일 입력(쿠폰 종료일 > 이벤트 종료일)")
    @Test
    void successGetCouponsForEventWithEventEndDate() throws Exception {
        // given
        Coupon coupon3 = CouponTestFactory.createCoupon3();

        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon3));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCouponsForEvent(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/for-event")
                        .param("page", "1")
                        .param("size", "10")
                        .param("eventEndDate", "2025-03-10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(3L))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value("3000원 할인쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-for-event-with-event-end-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.EVENT_END_DATE_PARAM))
                .andReturn();
    }

    @DisplayName("이벤트용 쿠폰 목록 조회 성공: 이벤트 시작일 & 종료일 입력")
    @Test
    void successGetCouponsForEventWithEventStartAndEndDate() throws Exception {
        // given
        List<CpnBriefResponseDto> coupons = List.of(
                CpnBriefResponseDto.from(coupon));

        PageImpl<CpnBriefResponseDto> page = new PageImpl<>(coupons);
        CpnPageResponseDto responseDto = CpnPageResponseDto.from(page);

        given(couponService.getCouponsForEvent(any(GetCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/coupons/for-event")
                        .param("page", "1")
                        .param("size", "10")
                        .param("eventStartDate", "2025-03-02")
                        .param("eventEndDate", "2025-03-10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.coupons[0].id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andDo(ApiDocumentFactory.listDoc(
                        "coupon-controller-test/success-get-coupons-for-event-with-event-start-and-end-date",
                        ApiDocumentFactory.COUPON_TAG,
                        ApiDocumentationLoader.getSummary("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentationLoader.getDescription("coupon", "EVENT_COUPON_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM,
                        ApiDocumentFactory.EVENT_START_DATE_PARAM,
                        ApiDocumentFactory.EVENT_END_DATE_PARAM))
                .andReturn();
    }
}