package salute.oneshot.domain.ingredientReview.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
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
import salute.oneshot.domain.ingredientReview.dto.request.CreateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.request.UpdateIngrReviewRequestDto;
import salute.oneshot.domain.ingredientReview.dto.response.IngrReviewResponseDto;
import salute.oneshot.domain.ingredientReview.dto.service.CreateIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetAllIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.GetMyIngredientReviewSDto;
import salute.oneshot.domain.ingredientReview.dto.service.UpdateIngrReviewSDto;
import salute.oneshot.domain.ingredientReview.service.IngredientReviewService;
import salute.oneshot.util.IngredientReviewTestFactory;
import salute.oneshot.util.IngredientTestFactory;
import salute.oneshot.util.UserTestFactory;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IngredientReviewController.class)
@Import(TestSecurityConfig.class)
class IngredientReviewControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "IngredientReview API";

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IngredientReviewService ingredientReviewService;

    @DisplayName("재료 리뷰 생성 성공")
    @Test
    void successCreateIngredientReview() throws Exception {
        // given
        CreateIngrReviewRequestDto requestDto = IngredientReviewTestFactory.createCreateIngrReviewRequestDto();
        IngrReviewResponseDto responseDto = IngredientReviewTestFactory.createIngrReviewResponseDto();

        given(ingredientReviewService.createIngredientReview(any(CreateIngrReviewSDto.class)))
            .willReturn(responseDto);

        // when & then
        mockMvc.perform(
                post("/api/ingredients/{ingredientId}/reviews", IngredientTestFactory.INGREDIENT_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto))
                    .with(user(UserTestFactory.createMockUserDetails())))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_INGR_RVW_SUCCESS))
            .andExpect(jsonPath("$.data.ingredient.ingredientId").value(IngredientTestFactory.INGREDIENT_ID))
            .andExpect(jsonPath("$.data.ingredient.name").value(IngredientTestFactory.NAME))
            .andExpect(jsonPath("$.data.ingredient.avb").value(IngredientTestFactory.AVB))
            .andExpect(jsonPath("$.data.ingredient.description").value(IngredientTestFactory.DESCRIPTION))
            .andExpect(jsonPath("$.data.ingredient.category").value(IngredientTestFactory.CATEGORY.toString()))
            .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
            .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
            .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
            .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))
            .andExpect(jsonPath("$.data.reviewId").value(IngredientReviewTestFactory.INGREDIENT_REVIEW_ID))
            .andExpect(jsonPath("$.data.star").value(IngredientReviewTestFactory.STAR.toString()))
            .andExpect(jsonPath("$.data.content").value(IngredientReviewTestFactory.CONTENT))
            .andDo(document("ingredientReview/createIngredient",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료 리뷰 생성")
                    .build()
                )));
    }

    @DisplayName("내 재료 리뷰 조회 성공")
    @Test
    void successGetMyIngredientReview() throws Exception {
        // given
        Page<IngrReviewResponseDto> responseDtoPage = IngredientReviewTestFactory.createIngrReviewResponseDtoPage();
        given(ingredientReviewService.getMyIngredientReview(any(GetMyIngredientReviewSDto.class)))
            .willReturn(responseDtoPage);

        // when & then
        mockMvc.perform(get("/api/ingredients/reviews/me")
                .queryParams(
                    MultiValueMap.fromSingleValue(
                        Map.of(
                            "page", "1",
                            "size", "10"
                        )))
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(UserTestFactory.createMockUserDetails()))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_RVW_LIST_SUCCESS))
            .andExpect(jsonPath("$.data.content[0].ingredient.ingredientId").value(IngredientTestFactory.INGREDIENT_ID))
            .andExpect(jsonPath("$.data.content[0].ingredient.name").value(IngredientTestFactory.NAME))
            .andExpect(jsonPath("$.data.content[0].ingredient.avb").value(IngredientTestFactory.AVB))
            .andExpect(jsonPath("$.data.content[0].ingredient.description").value(IngredientTestFactory.DESCRIPTION))
            .andExpect(jsonPath("$.data.content[0].ingredient.category").value(IngredientTestFactory.CATEGORY.toString()))
            .andExpect(jsonPath("$.data.content[0].user.id").value(UserTestFactory.USER_ID))
            .andExpect(jsonPath("$.data.content[0].user.email").value(UserTestFactory.EMAIL))
            .andExpect(jsonPath("$.data.content[0].user.nickName").value(UserTestFactory.NICKNAME))
            .andExpect(jsonPath("$.data.content[0].user.userRole").value(UserTestFactory.ROLE_USER.toString()))
            .andExpect(jsonPath("$.data.content[0].reviewId").value(IngredientReviewTestFactory.INGREDIENT_REVIEW_ID))
            .andExpect(jsonPath("$.data.content[0].star").value(IngredientReviewTestFactory.STAR.toString()))
            .andExpect(jsonPath("$.data.content[0].content").value(IngredientReviewTestFactory.CONTENT))
            .andDo(document("ingredientReview/myIngredientReview",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("내 재료 리뷰 조회")
                    .queryParameters(
                        parameterWithName("page").type(SimpleType.INTEGER).description("기본값 : 1").optional(),
                        parameterWithName("size").type(SimpleType.INTEGER).description("기본값 : 10").optional())
                    .build()
                )));
    }

    @DisplayName("재료 리뷰 단건 조회 성공")
    @Test
    void successGetRecipeReview() throws Exception {
        // given
        IngrReviewResponseDto responseDto = IngredientReviewTestFactory.createIngrReviewResponseDto();
        given(ingredientReviewService.getIngredientReview(any(Long.class)))
            .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/ingredients/reviews/{reviewsId}",
                IngredientReviewTestFactory.INGREDIENT_REVIEW_ID)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_RVW_SUCCESS))
            .andExpect(jsonPath("$.data.ingredient.ingredientId").value(IngredientTestFactory.INGREDIENT_ID))
            .andExpect(jsonPath("$.data.ingredient.name").value(IngredientTestFactory.NAME))
            .andExpect(jsonPath("$.data.ingredient.avb").value(IngredientTestFactory.AVB))
            .andExpect(jsonPath("$.data.ingredient.description").value(IngredientTestFactory.DESCRIPTION))
            .andExpect(jsonPath("$.data.ingredient.category").value(IngredientTestFactory.CATEGORY.toString()))
            .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
            .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
            .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
            .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))
            .andExpect(jsonPath("$.data.reviewId").value(IngredientReviewTestFactory.INGREDIENT_REVIEW_ID))
            .andExpect(jsonPath("$.data.star").value(IngredientReviewTestFactory.STAR.toString()))
            .andExpect(jsonPath("$.data.content").value(IngredientReviewTestFactory.CONTENT))
            .andDo(document("ingredientReview/getIngredientReview",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료 리뷰 단건 조회")
                    .build()
                )));
    }

    @DisplayName("재료 리뷰 전체 조회 성공")
    @Test
    void successGetAllIngredientReview() throws Exception {
        // given
        Page<IngrReviewResponseDto> responseDtoPage = IngredientReviewTestFactory.createIngrReviewResponseDtoPage();
        given(ingredientReviewService.getAllIngredientReview(any(GetAllIngrReviewSDto.class)))
            .willReturn(responseDtoPage);

        // when & then
        mockMvc.perform(
                get("/api/ingredients/{ingredientId}/reviews", IngredientTestFactory.INGREDIENT_ID)
                    .queryParams(
                        MultiValueMap.fromSingleValue(
                            Map.of(
                                "page", "1",
                                "size", "10"
                            )))
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(user(UserTestFactory.createMockUserDetails()))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_RVW_LIST_SUCCESS))
            .andExpect(jsonPath("$.data.content[0].ingredient.ingredientId").value(IngredientTestFactory.INGREDIENT_ID))
            .andExpect(jsonPath("$.data.content[0].ingredient.name").value(IngredientTestFactory.NAME))
            .andExpect(jsonPath("$.data.content[0].ingredient.avb").value(IngredientTestFactory.AVB))
            .andExpect(jsonPath("$.data.content[0].ingredient.description").value(IngredientTestFactory.DESCRIPTION))
            .andExpect(jsonPath("$.data.content[0].ingredient.category").value(IngredientTestFactory.CATEGORY.toString()))
            .andExpect(jsonPath("$.data.content[0].user.id").value(UserTestFactory.USER_ID))
            .andExpect(jsonPath("$.data.content[0].user.email").value(UserTestFactory.EMAIL))
            .andExpect(jsonPath("$.data.content[0].user.nickName").value(UserTestFactory.NICKNAME))
            .andExpect(jsonPath("$.data.content[0].user.userRole").value(UserTestFactory.ROLE_USER.toString()))
            .andExpect(jsonPath("$.data.content[0].reviewId").value(IngredientReviewTestFactory.INGREDIENT_REVIEW_ID))
            .andExpect(jsonPath("$.data.content[0].star").value(IngredientReviewTestFactory.STAR.toString()))
            .andExpect(jsonPath("$.data.content[0].content").value(IngredientReviewTestFactory.CONTENT))
            .andDo(document("ingredientReview/getAllIngredientReview",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료 리뷰 전체 조회")
                    .queryParameters(
                        parameterWithName("page").type(SimpleType.INTEGER).description("기본값 : 1").optional(),
                        parameterWithName("size").type(SimpleType.INTEGER).description("기본값 : 10").optional())
                    .build()
                )));
    }

    @DisplayName("재료 리뷰 수정 성공")
    @Test
    void successUpdateIngredientReview() throws Exception {
        // given
        UpdateIngrReviewRequestDto requestDto = IngredientReviewTestFactory.createUpdateIngrReviewRequestDto();
        IngrReviewResponseDto responseDto = IngredientReviewTestFactory.createIngrReviewResponseDto();
        given(ingredientReviewService.updateIngredientReview(any(UpdateIngrReviewSDto.class)))
            .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/ingredients/reviews/{reviewId}",
                IngredientReviewTestFactory.INGREDIENT_REVIEW_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
                .with(user(UserTestFactory.createMockUserDetails()))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_INGR_RVW_SUCCESS))
            .andExpect(jsonPath("$.data.ingredient.ingredientId").value(IngredientTestFactory.INGREDIENT_ID))
            .andExpect(jsonPath("$.data.ingredient.name").value(IngredientTestFactory.NAME))
            .andExpect(jsonPath("$.data.ingredient.avb").value(IngredientTestFactory.AVB))
            .andExpect(jsonPath("$.data.ingredient.description").value(IngredientTestFactory.DESCRIPTION))
            .andExpect(jsonPath("$.data.ingredient.category").value(IngredientTestFactory.CATEGORY.toString()))
            .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
            .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
            .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
            .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))
            .andExpect(jsonPath("$.data.reviewId").value(IngredientReviewTestFactory.INGREDIENT_REVIEW_ID))
            .andExpect(jsonPath("$.data.star").value(IngredientReviewTestFactory.STAR.toString()))
            .andExpect(jsonPath("$.data.content").value(IngredientReviewTestFactory.CONTENT))
            .andDo(document("ingredientReview/updateIngredientReview",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료 리뷰 수정")
                    .build()
                )));
    }

    @DisplayName("재료 리뷰 제거 성공")
    @Test
    void successDeleteIngredientReview() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/ingredients/reviews/{reviewId}",
                IngredientReviewTestFactory.INGREDIENT_REVIEW_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(UserTestFactory.createMockUserDetails()))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_INGR_RVW_SUCCESS))
            .andDo(document("ingredientReview/deleteIngredientReview",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료 리뷰 제거")
                    .build()
                )));
    }
}