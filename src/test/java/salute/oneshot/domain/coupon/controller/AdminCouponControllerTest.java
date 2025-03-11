package salute.oneshot.domain.coupon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.domain.coupon.dto.request.UserCpnRequestDto;
import salute.oneshot.domain.coupon.dto.response.CpnBriefResponseDto;
import salute.oneshot.domain.coupon.dto.response.CpnDetailResponseDto;
import salute.oneshot.domain.coupon.dto.response.UserCpnBriefResponseDto;
import salute.oneshot.domain.coupon.dto.service.CreateCpnSDto;
import salute.oneshot.domain.coupon.dto.service.CreateUserCpnSDto;
import salute.oneshot.domain.coupon.dto.service.UpdateCpnSDto;
import salute.oneshot.domain.coupon.entity.Coupon;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.CouponTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminCouponController.class)
@Import(TestSecurityConfig.class)
class AdminCouponControllerTest extends AbstractRestDocsTests {

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
        ReflectionTestUtils.setField(userCoupon,"id",1L);
    }

    @DisplayName("쿠폰 생성 성공")
    @Test
    void successCreateCoupon() throws Exception {
        // given
        CpnRequestDto requestDto =
                CouponTestFactory.createCpnRequestDto();
        CpnBriefResponseDto responseDto =
                CpnBriefResponseDto.from(coupon);

        given(couponService.createCoupon(any(CreateCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/admin/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.discountValue").value(CouponTestFactory.DISCOUNT_VALUE))
                .andReturn();
    }

    @DisplayName("쿠폰 수정 성공")
    @Test
    void successUpdateCoupon() throws Exception {
        // given
        CpnRequestDto requestDto =
                CouponTestFactory.createCpnRequestDto();
        CpnDetailResponseDto responseDto =
                CpnDetailResponseDto.from(coupon);

        given(couponService.updateCoupon(any(UpdateCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/admin/coupons/{couponId}", CouponTestFactory.COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.couponName").value(CouponTestFactory.COUPON_NAME))
                .andExpect(jsonPath("$.data.discountValue").value(CouponTestFactory.DISCOUNT_VALUE))
                .andExpect(jsonPath("$.data.startTime").value(CouponTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(CouponTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andReturn();
    }

    @DisplayName("쿠폰 수정 실패: 존재하지 않는 쿠폰아이디로 수정 시도")
    @Test
    void invalidCouponIdUpdateCoupon() throws Exception {
        // given
        CpnRequestDto requestDto =
                CouponTestFactory.createCpnRequestDto();

        given(couponService.updateCoupon(any(UpdateCpnSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/admin/coupons/{couponId}", CouponTestFactory.COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("쿠폰 삭제 성공")
    @Test
    void successDeleteCoupon() throws Exception {
        // given
        Long couponId = CouponTestFactory.COUPON_ID;
        given(couponService.deleteCoupon(couponId))
                .willReturn(couponId);

        // when & then
        mockMvc.perform(delete("/api/admin/coupons/{couponId}", couponId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_CPN_SUCCESS))
                .andExpect(jsonPath("$.data").value(couponId))
                .andReturn();
    }

    @DisplayName("쿠폰 삭제 실패: 존재하지 않는 쿠폰아이디로 삭제 시도")
    @Test
    void invalidCouponIdDeleteCoupon() throws Exception {
        // given
        Long couponId = CouponTestFactory.COUPON_ID;
        given(couponService.deleteCoupon(couponId))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(delete("/api/admin/coupons/{couponId}", couponId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("유저쿠폰 발급 성공")
    @Test
    void successGrantUserCoupon() throws Exception {
        // given
        UserCpnRequestDto requestDto =
                CouponTestFactory.createUserCpnRequestDTo();
        UserCpnBriefResponseDto responseDto =
                UserCpnBriefResponseDto.from(userCoupon);

        given(couponService.grantUserCoupon(any(CreateUserCpnSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/admin/coupons/{couponId}/users", CouponTestFactory.COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_USER_CPN_SUCCESS))
                .andExpect(jsonPath("$.data.userCouponId").value(1L))
                .andExpect(jsonPath("$.data.status").value(UserCouponStatus.ISSUED.toString()))
                .andExpect(jsonPath("$.data.coupon.couponName").value(CouponTestFactory.COUPON_NAME))
                .andReturn();
    }

    @DisplayName("유저쿠폰 발급 실패: 존재하지 않는 쿠폰아이디로 유저쿠폰 발급 시도")
    @Test
    void invalidCouponIdGrantUserCoupon() throws Exception {
        // given
        UserCpnRequestDto requestDto =
                CouponTestFactory.createUserCpnRequestDTo();

        given(couponService.grantUserCoupon(any(CreateUserCpnSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.COUPON_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/admin/coupons/{couponId}/users", CouponTestFactory.COUPON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COUPON_NOT_FOUND.getMessage()))
                .andReturn();
    }
}