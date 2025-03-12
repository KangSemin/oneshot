package salute.oneshot.domain.delivery.controller;

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
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.delivery.dto.request.DeliveryRequestDto;
import salute.oneshot.domain.delivery.dto.request.UpdateDeliveryRequestDto;
import salute.oneshot.domain.delivery.dto.response.AdminDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.service.AdminDeliverySDto;
import salute.oneshot.domain.delivery.dto.service.DeliverySDto;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.enums.CourierCompany;
import salute.oneshot.domain.delivery.enums.ShippingStatus;
import salute.oneshot.domain.delivery.service.DeliveryService;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.DeliveryTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminDeliveryController.class)
@Import(TestSecurityConfig.class)
class AdminDeliveryControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DeliveryService deliveryService;

    private Delivery delivery;

    @BeforeEach
    void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        delivery = DeliveryTestFactory.createDelivery();
    }

    @DisplayName("배송정보 등록 성공")
    @Test
    void successCreateDelivery() throws Exception {
        // given
        DeliveryRequestDto requestDto =
                DeliveryTestFactory.createDeliveryRequestDto();
        AdminDeliveryResponseDto responseDto =
                AdminDeliveryResponseDto.from(delivery);

        given(deliveryService.createDelivery(any(DeliverySDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/admin/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_DELIVERY_SUCCESS))
                .andExpect(jsonPath("$.data.deliveryId").value(DeliveryTestFactory.DELIVERY_ID))
                .andExpect(jsonPath("$.data.orderId").value(DeliveryTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.receiverName").value(DeliveryTestFactory.RECEIVER_NAVE))
                .andExpect(jsonPath("$.data.receiverPhone").value(DeliveryTestFactory.RECEIVER_PHONE))
                .andExpect(jsonPath("$.data.deliveryMessage").value(DeliveryTestFactory.DELIVERY_MESSAGE))
                .andExpect(jsonPath("$.data.courierCompany").value(CourierCompany.CJGLS.name()))
                .andExpect(jsonPath("$.data.trackingNumber").value(DeliveryTestFactory.TRACKING_NUMBER))
                .andExpect(jsonPath("$.data.status").value(ShippingStatus.REGISTERED.name()))
                .andExpect(jsonPath("$.data.createdAt").value("2025-03-11T00:00:00"))
                .andReturn();
    }

    @DisplayName("배송정보 등록 실패: 이미 배송정보가 존재하는 주문정보로 등록")
    @Test
    void conflictOrderIdCreateDelivery() throws Exception {
        // given
        DeliveryRequestDto requestDto =
                DeliveryTestFactory.createDeliveryRequestDto();

        given(deliveryService.createDelivery(any(DeliverySDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_SHIPPING));

        // when & then
        mockMvc.perform(post("/api/admin/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_SHIPPING.getMessage()))
                .andReturn();
    }

    @DisplayName("배송정보 등록 실패: 존재하지 않는 주문아이디로 등록")
    @Test
    void invalidOrderIdCreateDelivery() throws Exception {
        // given
        DeliveryRequestDto requestDto =
                DeliveryTestFactory.createDeliveryRequestDto();

        given(deliveryService.createDelivery(any(DeliverySDto.class)))
                .willThrow(new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/admin/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.ORDER_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배송정보 등록 실패: 유효하지 않은 주문상태로 등록")
    @Test
    void invalidOrderStateCreateDelivery() throws Exception {
        // given
        DeliveryRequestDto requestDto =
                DeliveryTestFactory.createDeliveryRequestDto();

        given(deliveryService.createDelivery(any(DeliverySDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_ORDER_STATE));

        // when & then
        mockMvc.perform(post("/api/admin/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_ORDER_STATE.getMessage()))
                .andReturn();
    }

    @DisplayName("배송 정보 조회 성공")
    @Test
    void successGetDelivery() throws Exception {
        // given
        Long DeliveryId = DeliveryTestFactory.DELIVERY_ID;
        AdminDeliveryResponseDto responseDto =
                AdminDeliveryResponseDto.from(delivery);

        given(deliveryService.getDelivery(DeliveryId))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_DELIVERY_SUCCESS))
                .andExpect(jsonPath("$.data.deliveryId").value(DeliveryTestFactory.DELIVERY_ID))
                .andExpect(jsonPath("$.data.orderId").value(DeliveryTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.receiverName").value(DeliveryTestFactory.RECEIVER_NAVE))
                .andExpect(jsonPath("$.data.receiverPhone").value(DeliveryTestFactory.RECEIVER_PHONE))
                .andExpect(jsonPath("$.data.deliveryMessage").value(DeliveryTestFactory.DELIVERY_MESSAGE))
                .andExpect(jsonPath("$.data.courierCompany").value(CourierCompany.CJGLS.name()))
                .andExpect(jsonPath("$.data.trackingNumber").value(DeliveryTestFactory.TRACKING_NUMBER))
                .andExpect(jsonPath("$.data.status").value(ShippingStatus.REGISTERED.name()))
                .andExpect(jsonPath("$.data.createdAt").value("2025-03-11T00:00:00"))
                .andReturn();
    }

    @DisplayName("배송 정보 조회 실패: 존재하지 않는 딜리버리아이디로 조회")
    @Test
    void invalidDeliveryIdGetDelivery() throws Exception {
        // given
        Long DeliveryId = DeliveryTestFactory.DELIVERY_ID;

        given(deliveryService.getDelivery(DeliveryId))
                .willThrow(new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.SHIPPING_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배송 정보 수정 성공")
    @Test
    void successUpdateDelivery() throws Exception {
        // given
        UpdateDeliveryRequestDto requestDto =
                UpdateDeliveryRequestDto.of("registered");

        AdminDeliveryResponseDto responseDto =
                AdminDeliveryResponseDto.from(delivery);

        given(deliveryService.updateDelivery(any(AdminDeliverySDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_DELIVERY_SUCCESS))
                .andExpect(jsonPath("$.data.deliveryId").value(DeliveryTestFactory.DELIVERY_ID))
                .andExpect(jsonPath("$.data.orderId").value(DeliveryTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.receiverName").value(DeliveryTestFactory.RECEIVER_NAVE))
                .andExpect(jsonPath("$.data.receiverPhone").value(DeliveryTestFactory.RECEIVER_PHONE))
                .andExpect(jsonPath("$.data.deliveryMessage").value(DeliveryTestFactory.DELIVERY_MESSAGE))
                .andExpect(jsonPath("$.data.courierCompany").value(CourierCompany.CJGLS.name()))
                .andExpect(jsonPath("$.data.trackingNumber").value(DeliveryTestFactory.TRACKING_NUMBER))
                .andExpect(jsonPath("$.data.status").value(ShippingStatus.of(requestDto.getStatus()).toString()))
                .andExpect(jsonPath("$.data.createdAt").value("2025-03-11T00:00:00"))
                .andReturn();
    }

    @DisplayName("배송 정보 수정 실패: 존재하지 않는 딜리버리아이디로 수정")
    @Test
    void invalidDeliveryIdUpdateDelivery() throws Exception {
        // given
        UpdateDeliveryRequestDto requestDto =
                UpdateDeliveryRequestDto.of("registered");

        given(deliveryService.updateDelivery(any(AdminDeliverySDto.class)))
                .willThrow(new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.SHIPPING_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("배송 정보 수정 실패: 같은 배송상태로 변경 시도")
    @Test
    void conflictDeliveryStatusUpdateDelivery() throws Exception {
        // given
        UpdateDeliveryRequestDto requestDto =
                UpdateDeliveryRequestDto.of("registered");

        given(deliveryService.updateDelivery(any(AdminDeliverySDto.class)))
                .willThrow(new InvalidException(ErrorCode.SAME_STATUS_UPDATE));

        // when & then
        mockMvc.perform(patch("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.SAME_STATUS_UPDATE.getMessage()))
                .andReturn();
    }

    @DisplayName("배송 정보 수정 실패: 유효하지 않은 순서로 상태 변경 시도")
    @Test
    void invalidDeliveryStatusUpdateDelivery() throws Exception {
        // given
        UpdateDeliveryRequestDto requestDto =
                UpdateDeliveryRequestDto.of("registered");

        given(deliveryService.updateDelivery(any(AdminDeliverySDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_STATUS_CHANGE));

        // when & then
        mockMvc.perform(patch("/api/admin/deliveries/{deliveryId}", DeliveryTestFactory.DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_STATUS_CHANGE.getMessage()))
                .andReturn();
    }
}