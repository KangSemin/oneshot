package salute.oneshot.domain.cart.controller;

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
import salute.oneshot.domain.cart.dto.request.AddCartItemRequestDto;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;
import salute.oneshot.domain.cart.dto.response.CartItemResponseDto;
import salute.oneshot.domain.cart.dto.response.CartResponseDto;
import salute.oneshot.domain.cart.dto.service.AddCartItemSDto;
import salute.oneshot.domain.cart.dto.service.UpdateItemQuantitySDto;
import salute.oneshot.domain.cart.service.CartService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.util.CartTestFactory;
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

@WebMvcTest(controllers = CartController.class)
@Import(TestSecurityConfig.class)
class CartControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Cart API";

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CartService cartService;

    @DisplayName("장바구니 항목 추가 성공")
    @Test
    void successAddItem() throws Exception {
        // given
        AddCartItemRequestDto requestDto = CartTestFactory.createAddCartItemRequestDto();
        CartItemResponseDto responseDto = CartTestFactory.createCartItemResponseDto();

        given(cartService.addCartItem(any(AddCartItemSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/carts/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_CART_ITEM_SUCCESS))
                .andExpect(jsonPath("$.data.cartItemId").value(CartTestFactory.CART_ITEM_ID))
                .andExpect(jsonPath("$.data.quantity").value(CartTestFactory.QUANTITY))
                .andExpect(jsonPath("$.data.product.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.product.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.product.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.product.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.product.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("cart/addItem",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("장바구니 항목 추가 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("장바구니 조회 성공")
    @Test
    void successFindCart() throws Exception {
        // given
        CartResponseDto responseDto = CartTestFactory.createCartResponseDto();
        given(cartService.findCart(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CART_SUCCESS))

                .andExpect(jsonPath("$.data.itemList[0].cartItemId").value(CartTestFactory.CART_ITEM_ID))
                .andExpect(jsonPath("$.data.itemList[0].quantity").value(CartTestFactory.QUANTITY))
                .andExpect(jsonPath("$.data.itemList[0].product.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.itemList[0].product.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.itemList[0].product.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.itemList[0].product.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.itemList[0].product.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("cart/findCart",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("장바구니 조회 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("장바구니 항목 수량 변경 성공")
    @Test
    void successUpdateItemQuantity() throws Exception {
        // given
        UpdateCartItemAmountRequestDto requestDto = CartTestFactory.createUpdateCartItemAmountRequestDto();
        CartItemResponseDto responseDto = CartTestFactory.createCartItemResponseDto();

        given(cartService.updateItemQuantity(any(UpdateItemQuantitySDto.class)))
                .willReturn(responseDto);


        // when & then
        mockMvc.perform(patch("/api/carts/items/{itemId}", CartTestFactory.CART_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_CART_ITEM_QUANTITY_SUCCESS))

                .andExpect(jsonPath("$.data.cartItemId").value(CartTestFactory.CART_ITEM_ID))
                .andExpect(jsonPath("$.data.quantity").value(CartTestFactory.QUANTITY))

                .andExpect(jsonPath("$.data.product.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data.product.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data.product.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.product.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data.product.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))

                .andDo(document("cart/updateItemQuantity",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("장바구니 항목 수량 변경 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("장바구니 항목 제거 성공")
    @Test
    void successRemoveItem() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/carts/items/{itemId}", CartTestFactory.CART_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.REMOVE_CART_ITEM_SUCCESS))

                .andDo(document("cart/removeItem",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("장바구니 항목 제거 성공")
                                .build())))
                .andReturn();
    }

    @DisplayName("장바구니 비우기 성공")
    @Test
    void successEmptyCart() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.EMPTY_CART_SUCCESS))

                .andDo(document("cart/emptyCart",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("장바구니 비우기 성공")
                                .build())))
                .andReturn();
    }
}