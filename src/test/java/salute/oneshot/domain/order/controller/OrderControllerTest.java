package salute.oneshot.domain.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.request.UpdateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.dto.service.CreateOrderSDto;
import salute.oneshot.domain.order.dto.service.GetAllOrderSDto;
import salute.oneshot.domain.order.dto.service.GetOrderSDto;
import salute.oneshot.domain.order.dto.service.UpdateOrderSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.OrderTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@Import(TestSecurityConfig.class)
class OrderControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private JwtProvider jwtProvider;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private OrderPaymentFacade orderPaymentFacade;

    @DisplayName("주문 생성 성공")
    @Test
    void successCreateOrder() throws Exception {
        // given
        CreateOrderRequestDto requestDto = OrderTestFactory.createCreateOrderRequestDto();
        CreateOrderResponseDto responseDto = OrderTestFactory.createCreateOrderResponseDto();

        given(orderPaymentFacade.createOrder(any(CreateOrderSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_ORD_SUCCESS))
                .andExpect(jsonPath("$.data.orderId").value(responseDto.getOrderId()))
                .andExpect(jsonPath("$.data.orderNumber").value(responseDto.getOrderNumber()))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.amount").value(responseDto.getAmount()))
                .andExpect(jsonPath("$.data.status").value(responseDto.getStatus().toString()))
                .andExpect(jsonPath("$.data.orderDate").value(responseDto.getOrderDate().toString()))
                .andReturn();
    }

    @DisplayName("주문 단건 조회 성공")
    @Test
    void successGetOrder() throws Exception {
        // given
        GetOrderResponseDto responseDto = OrderTestFactory.createGetOrderResponseDto();
        given(orderPaymentFacade.getOrder(any(GetOrderSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/orders/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ORD_SUCCESS))
                .andExpect(jsonPath("$.data.orderId").value(responseDto.getOrderId()))
                .andExpect(jsonPath("$.data.orderNumber").value(responseDto.getOrderNumber()))
                .andExpect(jsonPath("$.data.amount").value(responseDto.getAmount()))
                .andExpect(jsonPath("$.data.orderItems[0].productName").value(responseDto.getOrderItems().get(0).getProductName()))
                .andExpect(jsonPath("$.data.orderItems[0].quantity").value(responseDto.getOrderItems().get(0).getQuantity()))
                .andExpect(jsonPath("$.data.orderItems[0].price").value(responseDto.getOrderItems().get(0).getPrice()))
                .andReturn();
    }

    @DisplayName("주문 전체 조회 성공")
    @Test
    void successGetAllOrder() throws Exception {
        // given
        User user = UserTestFactory.createUser();
        Page<CreateOrderResponseDto> responseDtoPage = OrderTestFactory.createCreateOrderResponseDtoPage();

        given(orderPaymentFacade.getAllOrder(any(GetAllOrderSDto.class)))
                .willReturn(responseDtoPage);


        // when & then
        mockMvc.perform(get("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ORD_SUCCESS))
                .andExpect(jsonPath("$.data.content[0].orderId").value(responseDtoPage.getContent().get(0).getOrderId()))
                .andExpect(jsonPath("$.data.content[0].orderNumber").value(responseDtoPage.getContent().get(0).getOrderNumber()))
                .andExpect(jsonPath("$.data.content[0].name").value(responseDtoPage.getContent().get(0).getName()))
                .andExpect(jsonPath("$.data.content[0].amount").value(responseDtoPage.getContent().get(0).getAmount()))
                .andExpect(jsonPath("$.data.content[0].status").value(responseDtoPage.getContent().get(0).getStatus().toString()))
                .andExpect(jsonPath("$.data.content[0].orderDate").value(responseDtoPage.getContent().get(0).getOrderDate().toString()))
                .andReturn();
    }

    @DisplayName("주문 수정 성공")
    @Test
    void successUpdateOrder() throws Exception {
        // given
        UpdateOrderRequestDto requestDto = OrderTestFactory.createUpdateOrderRequestDto();
        UpdateOrderResponseDto responseDto = OrderTestFactory.createUpdateOrderResponseDto();

        given(orderPaymentFacade.updateOrder(any(UpdateOrderSDto.class)))
                .willReturn(responseDto);
        // when & then
        mockMvc.perform(patch("/api/orders/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_ORD_SUCCESS))
                .andExpect(jsonPath("$.data.orderId").value(responseDto.getOrderId()))
                .andExpect(jsonPath("$.data.amount").value(responseDto.getAmount()))
                .andExpect(jsonPath("$.data.status").value(responseDto.getStatus().toString()))
                .andExpect(jsonPath("$.data.updateDate").value(responseDto.getUpdateDate().toString()))
                .andReturn();
    }

    @DisplayName("주문 제거 성공")
    @Test
    void successDeleteOrder() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/orders/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_ORD_SUCCESS))
                .andReturn();
    }
}