package salute.oneshot.domain.order.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
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
import salute.oneshot.util.OrderTestFactory;
import salute.oneshot.util.ProductTestFactory;
import salute.oneshot.util.UserTestFactory;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@Import(TestSecurityConfig.class)
class OrderControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Order API";

    @Autowired
    private ObjectMapper objectMapper;

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

                .andExpect(jsonPath("$.data.orderId").value(OrderTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.orderNumber").value(OrderTestFactory.ORDER_NUMBER))
                .andExpect(jsonPath("$.data.name").value(OrderTestFactory.NAME))
                .andExpect(jsonPath("$.data.amount").value(OrderTestFactory.AMOUNT))
                .andExpect(jsonPath("$.data.status").value(OrderTestFactory.STATUS.toString()))
                .andExpect(jsonPath("$.data.orderDate").value(OrderTestFactory.ORDER_DATE.toString()))

                .andDo(document("order/createOrder",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("주문 생성 성공")
                                .build())))
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
        mockMvc.perform(get("/api/orders/{orderId}", OrderTestFactory.ORDER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ORD_SUCCESS))

                .andExpect(jsonPath("$.data.orderId").value(OrderTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.orderNumber").value(OrderTestFactory.ORDER_NUMBER))
                .andExpect(jsonPath("$.data.amount").value(OrderTestFactory.AMOUNT))

                .andExpect(jsonPath("$.data.orderItems[0].productName").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.orderItems[0].quantity").value(OrderTestFactory.ORDER_ITEM_QUANTITY))
                .andExpect(jsonPath("$.data.orderItems[0].price").value(ProductTestFactory.PRICE))

                .andDo(document("order/getOrder",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("주문 단건 조회 성공")
                                .build())))
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

                .andExpect(jsonPath("$.data.content[0].orderId").value(OrderTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.content[0].orderNumber").value(OrderTestFactory.ORDER_NUMBER))
                .andExpect(jsonPath("$.data.content[0].name").value(OrderTestFactory.NAME))
                .andExpect(jsonPath("$.data.content[0].amount").value(OrderTestFactory.AMOUNT))
                .andExpect(jsonPath("$.data.content[0].status").value(OrderTestFactory.STATUS.toString()))
                .andExpect(jsonPath("$.data.content[0].orderDate").value(OrderTestFactory.ORDER_DATE.toString()))

                .andDo(document("order/getAllOrder",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("주문 전체 조회 성공")
                                .build())))
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
        mockMvc.perform(patch("/api/orders/{orderId}", OrderTestFactory.ORDER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_ORD_SUCCESS))

                .andExpect(jsonPath("$.data.orderId").value(OrderTestFactory.ORDER_ID))
                .andExpect(jsonPath("$.data.amount").value(OrderTestFactory.AMOUNT))
                .andExpect(jsonPath("$.data.status").value(OrderTestFactory.STATUS.toString()))
                .andExpect(jsonPath("$.data.updateDate").value(OrderTestFactory.ORDER_DATE.toString()))

                .andDo(document("order/updateOrder",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("주문 수정 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("주문 제거 성공")
    @Test
    void successDeleteOrder() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/orders/{orderId}", OrderTestFactory.ORDER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_ORD_SUCCESS))

                .andDo(document("order/deleteOrder",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("주문 제거 성공")
                                .build())))
                .andReturn();
    }
}