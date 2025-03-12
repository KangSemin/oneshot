package salute.oneshot.domain.product.controller;

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
import org.springframework.util.MultiValueMap;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.product.dto.request.CreateProductRequestDto;
import salute.oneshot.domain.product.dto.request.UpdateProductRequestDto;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.dto.service.CreateProductSDto;
import salute.oneshot.domain.product.dto.service.GetAllProductSDto;
import salute.oneshot.domain.product.dto.service.UpdateProductRequestSDto;
import salute.oneshot.domain.product.service.ProductService;
import salute.oneshot.util.ProductTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@Import(TestSecurityConfig.class)
class ProductControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Product API";

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @DisplayName("상품 생성 성공")
    @Test
    void successCreateProduct() throws Exception {
        // given
        CreateProductRequestDto requestDto = ProductTestFactory.createCreateProductRequestDto();
        ProductResponseDto responseDto = ProductTestFactory.createProductResponseDto();

        given(productService.createProduct(any(CreateProductSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_PRDT_SUCCESS))

                .andExpect(jsonPath("$.data.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("product/createProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("상품 생성 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("상품 수정 성공")
    @Test
    void successUpdateProduct() throws Exception {
        // given
        UpdateProductRequestDto requestDto = ProductTestFactory.createUpdateProductRequestDto();
        ProductResponseDto responseDto = ProductTestFactory.createProductResponseDto();
        given(productService.updateProduct(any(UpdateProductRequestSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/products/{productId}", ProductTestFactory.PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_PRDT_SUCCESS))

                .andExpect(jsonPath("$.data.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("product/updateProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("상품 수정 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("상품 단건 조회 성공")
    @Test
    void successGetProduct() throws Exception {
        // given
        ProductResponseDto responseDto = ProductTestFactory.createProductResponseDto();

        given(productService.getProduct(ProductTestFactory.PRODUCT_ID))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/products/{productId}", ProductTestFactory.PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_PRDT_SUCCESS))

                .andExpect(jsonPath("$.data.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("product/getProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("상품 단건 조회 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("상품 전체 조회 성공")
    @Test
    void successGetAllProduct() throws Exception {
        // given
        Page<ProductResponseDto> responseDtoPage = ProductTestFactory.createProductResponseDtoPage();

        given(productService.getAllProduct(any(GetAllProductSDto.class)))
                .willReturn(responseDtoPage);

        // when & then
        mockMvc.perform(get("/api/products")
                        .queryParams(
                                MultiValueMap.fromSingleValue(
                                        Map.of(
                                                "category", "ALCOHOL",
                                                "page", "1",
                                                "size", "10"
                                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_PRDT_SUCCESS))

                .andExpect(jsonPath("$.data.content[0].productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.content[0].name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.content[0].description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.content[0].price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.content[0].stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("product/getAllProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("상품 전체 조회 성공")
                                .queryParameters(
                                        parameterWithName("category").description("상품의 카테고리").optional(),
                                        parameterWithName("page").description("페이지 넘버").optional(),
                                        parameterWithName("size").description("페이지당 항목 수").optional())
                                .build())))
                .andReturn();
    }

    @DisplayName("상품 제거 성공")
    @Test
    void successDeleteProduct() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/products/{productId}", ProductTestFactory.PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_PRDT_SUCCESS))

                .andDo(document("product/deleteProduct",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("상품 제거 성공")
                                .build())))
                .andReturn();
    }
}