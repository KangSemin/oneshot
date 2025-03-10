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
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
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

    @BeforeEach
    void setUp() {
        coupon = CouponTestFactory.createCoupon();
        user = UserTestFactory.createUser();
        userCoupon = UserCoupon.of(user, coupon);
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
                .andExpect(jsonPath("$.data.status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andReturn();
    }

    @DisplayName("유저쿠폰 사용 실패: 존재하지 않는 유저 쿠폰 아이디로 사용 시도")
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
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공")
    @Test
    void successGetCoupons() throws Exception {
        // given
        List<CpnBriefResponseDto> coupons = List.of(CpnBriefResponseDto.from(coupon));
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
                .andExpect(jsonPath("$.data.coupons[0].couponName").value(CouponTestFactory.COUPON_NAME))
                .andReturn();
    }

    @DisplayName("쿠폰 목록 조회 성공 - 빈 목록 조회")
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
                .andExpect(jsonPath("$.data.couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.discountValue").value(CouponTestFactory.DISCOUNT_VALUE))
                .andExpect(jsonPath("$.data.startTime").value(CouponTestFactory.formatDateTime(CouponTestFactory.START_LOCAL_DATE_TIME)))
                .andExpect(jsonPath("$.data.endTime").value(CouponTestFactory.formatDateTime(CouponTestFactory.END_LOCAL_DATE_TIME)))
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
                .andExpect(jsonPath("$.data.userCoupons[0].status").value(UserCouponStatus.ISSUED.toString()))
                .andReturn();
    }

    @DisplayName("유저쿠폰 목록 조회 성공 - 빈 목록 조회")
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
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CPN_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.userCoupons").isArray())
                .andExpect(jsonPath("$.data.userCoupons").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
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
                .andExpect(jsonPath("$.data.status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.coupon.couponName").value(CouponTestFactory.COUPON_NAME))
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
                .andReturn();
    }
}