package salute.oneshot.domain.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
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
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.CartTestFactory;
import salute.oneshot.util.ProductTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class)
@Import(TestSecurityConfig.class)
class CartControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private JwtProvider jwtProvider;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private CartService cartService;

    @DisplayName("장바구니 항목 추가 성공")
    @Test
    void successAddItem() throws Exception {
        // given
        AddCartItemRequestDto requestDto = CartTestFactory.createAddCartItemRequestDto();
        CartItemResponseDto responseDto = CartTestFactory.createCartItemResponseDto(UserTestFactory.createUser());

        given(cartService.addCartItem(any(AddCartItemSDto.class)))
                .willReturn(responseDto);

        // when & then
        ResultActions resultActions = mockMvc.perform(post("/api/carts/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_CART_ITEM_SUCCESS))
                .andExpect(jsonPath("$.data.cartItemId").value(CartTestFactory.CART_ITEM_ID))
                .andExpect(jsonPath("$.data.quantity").value(CartTestFactory.QUANTITY));

        resultActionsForProduct(resultActions, "")
                .andReturn();
    }

    @DisplayName("장바구니 조회 성공")
    @Test
    void successFindCart() throws Exception {
        // given
        CartResponseDto responseDto = CartTestFactory.createCartResponseDto(UserTestFactory.createUser());
        given(cartService.findCart(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        ResultActions resultActions = mockMvc.perform(get("/api/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_CART_SUCCESS))
                .andExpect(jsonPath("$.data.itemList[0].cartItemId").value(responseDto.getItemList().get(0).getCartItemId()))
                .andExpect(jsonPath("$.data.itemList[0].quantity").value(responseDto.getItemList().get(0).getQuantity()));

        resultActionsForProduct(resultActions, ".itemList[0]")
                .andReturn();
    }

    @DisplayName("장바구니 항목 수량 변경 성공")
    @Test
    void successUpdateItemQuantity() throws Exception {
        // given
        UpdateCartItemAmountRequestDto requestDto = CartTestFactory.createUpdateCartItemAmountRequestDto();
        User user = UserTestFactory.createUser();
        CartItemResponseDto responseDto = CartTestFactory.createCartItemResponseDto(user);

        given(cartService.updateItemQuantity(any(UpdateItemQuantitySDto.class)))
                .willReturn(responseDto);


        // when & then
        ResultActions resultActions = mockMvc.perform(patch("/api/carts/items/" + CartTestFactory.CART_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_CART_ITEM_QUANTITY_SUCCESS))
                .andExpect(jsonPath("$.data.cartItemId").value(responseDto.getCartItemId()))
                .andExpect(jsonPath("$.data.quantity").value(responseDto.getQuantity()));

        resultActionsForProduct(resultActions, "")
                .andReturn();
    }

    @DisplayName("장바구니 항목 제거 성공")
    @Test
    void successRemoveItem() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/carts/items/" + CartTestFactory.CART_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNoContent())
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
                .andReturn();
    }

    ResultActions resultActionsForProduct(ResultActions resultActions, String path) throws Exception {
        return resultActions
                .andExpect(jsonPath("$.data" + path + ".product.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data" + path + ".product.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data" + path + ".product.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data" + path + ".product.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data" + path + ".product.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))
    }

    ResultActions resultActionsForCartItem(ResultActions resultActions, String path) throws Exception {
        resultActionsForProduct()
        return resultActions
                .andExpect(jsonPath("$.data" + path + ".product.productId").value(ProductTestFactory.PRODUCT_ID))
                .andExpect(jsonPath("$.data" + path + ".product.name").value(ProductTestFactory.NAME))
                .andExpect(jsonPath("$.data" + path + ".product.description").value(ProductTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data" + path + ".product.price").value(ProductTestFactory.PRICE))
                .andExpect(jsonPath("$.data" + path + ".product.stockQuantity").value(ProductTestFactory.STOCK_QUANTITY))
    }

}