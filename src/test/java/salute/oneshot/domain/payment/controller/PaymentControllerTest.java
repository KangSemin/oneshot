package salute.oneshot.domain.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.PaymentTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class)
@Import(TestSecurityConfig.class)
class PaymentControllerTest extends AbstractRestDocsTests {

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

    @DisplayName("결제 승인 성공")
    @Test
    void successConfirmPayment() throws Exception {
        // given
        ConfirmPaymentRequestDto requestDto = PaymentTestFactory.createConfirmPaymentRequestDto();
        PaymentResponseDto responseDto = PaymentTestFactory.createPaymentResponseDto();

        given(orderPaymentFacade.confirmPayment(any(ConfirmPaymentSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/payments/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_PMNT_SUCCESS))
                .andExpect(jsonPath("$.data.paymentKey").value(responseDto.getPaymentKey()))
                .andExpect(jsonPath("$.data.status").value(responseDto.getStatus().toString()))
                .andExpect(jsonPath("$.data.orderId").value(responseDto.getOrderId()))
                .andExpect(jsonPath("$.data.orderName").value(responseDto.getOrderName()))
                .andExpect(jsonPath("$.data.totalAmount").value(responseDto.getTotalAmount()))
                .andReturn();
    }

    @DisplayName("결제 조회 성공")
    @Test
    void successFindPayment() throws Exception {
        // given
        PaymentResponseDto responseDto = PaymentTestFactory.createPaymentResponseDto();
        given(orderPaymentFacade.getPayment(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/payments/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_PMNT_SUCCESS))
                .andExpect(jsonPath("$.data.paymentKey").value(responseDto.getPaymentKey()))
                .andExpect(jsonPath("$.data.status").value(responseDto.getStatus().toString()))
                .andExpect(jsonPath("$.data.orderId").value(responseDto.getOrderId()))
                .andExpect(jsonPath("$.data.orderName").value(responseDto.getOrderName()))
                .andExpect(jsonPath("$.data.totalAmount").value(responseDto.getTotalAmount()))
                .andReturn();
    }
}