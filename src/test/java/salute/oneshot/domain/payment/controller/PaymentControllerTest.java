package salute.oneshot.domain.payment.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.payment.dto.request.ConfirmPaymentRequestDto;
import salute.oneshot.domain.payment.dto.response.PaymentResponseDto;
import salute.oneshot.domain.payment.dto.service.ConfirmPaymentSDto;
import salute.oneshot.util.OrderTestFactory;
import salute.oneshot.util.PaymentTestFactory;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class)
@Import(TestSecurityConfig.class)
class PaymentControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Payment API";

    @Autowired
    private ObjectMapper objectMapper;

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

                .andExpect(jsonPath("$.data.paymentKey").value(PaymentTestFactory.PAYMENT_KEY))
                .andExpect(jsonPath("$.data.status").value(PaymentTestFactory.STATUS.toString()))
                .andExpect(jsonPath("$.data.orderId").value(OrderTestFactory.ORDER_NUMBER))
                .andExpect(jsonPath("$.data.orderName").value(OrderTestFactory.NAME))
                .andExpect(jsonPath("$.data.totalAmount").value(OrderTestFactory.AMOUNT))

                .andDo(document("payment/confirmPayment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("결제 승인 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("결제 조회 성공")
    @Test
    void successFindPayment() throws Exception {
        // given
        PaymentResponseDto responseDto = PaymentTestFactory.createPaymentResponseDto();
        given(orderPaymentFacade.getPayment(any(String.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/payments/{paymentId}", PaymentTestFactory.PAYMENT_KEY)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_PMNT_SUCCESS))

                .andExpect(jsonPath("$.data.paymentKey").value(PaymentTestFactory.PAYMENT_KEY))
                .andExpect(jsonPath("$.data.status").value(PaymentTestFactory.STATUS.toString()))
                .andExpect(jsonPath("$.data.orderId").value(OrderTestFactory.ORDER_NUMBER))
                .andExpect(jsonPath("$.data.orderName").value(OrderTestFactory.NAME))
                .andExpect(jsonPath("$.data.totalAmount").value(OrderTestFactory.AMOUNT))

                .andDo(document("payment/findPayment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("결제 조회 성공")
                                .build())))
                .andReturn();
    }
}