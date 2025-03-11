package salute.oneshot.domain.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.delivery.dto.response.UserDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.service.UserDeliverySDto;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.enums.CourierCompany;
import salute.oneshot.domain.delivery.enums.ShippingStatus;
import salute.oneshot.domain.delivery.service.DeliveryService;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.DeliveryTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeliveryController.class)
@Import(TestSecurityConfig.class)
class DeliveryControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DeliveryService deliveryService;

    private Delivery delivery;

    @BeforeEach
    void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        delivery = DeliveryTestFactory.createDelivery();
    }

    @DisplayName("유저의 배송정보 조회 성공")
    @Test
    void successGetShippingForUser() throws Exception {
        // given
        UserDeliveryResponseDto responseDto =
                UserDeliveryResponseDto.from(delivery);

        given(deliveryService.getDeliveryByOrderId(any(UserDeliverySDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/deliveries/orders/{orderId}", DeliveryTestFactory.ORDER_ID)
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_DELIVERY_SUCCESS))
                .andExpect(jsonPath("$.data.deliveryId").value(DeliveryTestFactory.DELIVERY_ID))
                .andExpect(jsonPath("$.data.orderId").value(DeliveryTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.courierName").value(CourierCompany.ofName(DeliveryTestFactory.COURIER_COMPANY).getCompanyName()))
                .andExpect(jsonPath("$.data.trackingNumber").value(DeliveryTestFactory.TRACKING_NUMBER))
                .andExpect(jsonPath("$.data.status").value(ShippingStatus.REGISTERED.toString()))
                .andExpect(jsonPath("$.data.deliveryMessage").value(DeliveryTestFactory.DELIVERY_MESSAGE))
                .andReturn();
    }

    @DisplayName("유저의 배송정보 조회 실패: 존재하지 않는 오더아이디로 조회")
    @Test
    void invalidOrderIdGetShippingForUser() throws Exception {
        // given
        given(deliveryService.getDeliveryByOrderId(any(UserDeliverySDto.class)))
                .willThrow(new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/deliveries/orders/{orderId}", DeliveryTestFactory.ORDER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.SHIPPING_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("유저의 배송정보 조회 실패: 타인의 오더아이디로 배송정보를 조회")
    @Test
    void forbiddenOrderIdGetShippingForUser() throws Exception {
        // given
        given(deliveryService.getDeliveryByOrderId(any(UserDeliverySDto.class)))
                .willThrow(new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS));

        // when & then
        mockMvc.perform(get("/api/deliveries/orders/{orderId}", DeliveryTestFactory.ORDER_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isForbidden())
                 .andExpect(jsonPath("$.errorMessage").value(ErrorCode.FORBIDDEN_ACCESS.getMessage()))
                .andReturn();
    }
}