package salute.oneshot.domain.recipeReview;

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
import salute.oneshot.domain.recipeReview.controller.RecipeReviewController;
import salute.oneshot.domain.recipeReview.dto.request.CreateRecipeReviewRequestDto;
import salute.oneshot.domain.recipeReview.dto.request.UpdateRecipeRequestDto;
import salute.oneshot.domain.recipeReview.dto.response.RecipeReviewResponseDto;
import salute.oneshot.domain.recipeReview.dto.service.CreateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.dto.service.GetAllRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.dto.service.GetMyRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.dto.service.UpdateRecipeReviewSDto;
import salute.oneshot.domain.recipeReview.service.RecipeReviewService;
import salute.oneshot.util.CocktailTestFactory;
import salute.oneshot.util.RecipeReviewTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeReviewController.class)
@Import(TestSecurityConfig.class)
class RecipeReviewControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RecipeReviewService recipeReviewService;

    @DisplayName("레시피 리뷰 생성 성공")
    @Test
    void successCreateRecipeReview() throws Exception {
        // given
        CreateRecipeReviewRequestDto requestDto = RecipeReviewTestFactory.createCreateRecipeReviewRequestDto();
        RecipeReviewResponseDto responseDto = RecipeReviewTestFactory.createRecipeReviewResponseDto();

        given(recipeReviewService.createRecipeReview(any(CreateRecipeReviewSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/recipes/" + RecipeReviewTestFactory.RECIPE_REVIEW_ID + "/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.data.cocktail.id").value(CocktailTestFactory.COCKTAIL_ID))
                .andExpect(jsonPath("$.data.cocktail.name").value(CocktailTestFactory.NAME))
                .andExpect(jsonPath("$.data.cocktail.description").value(CocktailTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.cocktail.recipe").value(CocktailTestFactory.RECIPE))
                .andExpect(jsonPath("$.data.cocktail.type").value(CocktailTestFactory.TYPE.toString()))
                .andExpect(jsonPath("$.data.cocktail.likeCounts").value(CocktailTestFactory.LIKE_COUNT))
                .andExpect(jsonPath("$.data.cocktail.userId").value(UserTestFactory.USER_ID))

                .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))

                .andExpect(jsonPath("$.data.reviewId").value(RecipeReviewTestFactory.RECIPE_REVIEW_ID))
                .andExpect(jsonPath("$.data.star").value(RecipeReviewTestFactory.STAR.toString()))
                .andExpect(jsonPath("$.data.content").value(RecipeReviewTestFactory.CONTENT))
                .andReturn();
    }

    @DisplayName("내 레시피 리뷰 조회 성공")
    @Test
    void successGetMyRecipeReview() throws Exception {
        // given
        Page<RecipeReviewResponseDto> responseDtoPage = RecipeReviewTestFactory.createRecipeReviewResponseDtoPage();
        given(recipeReviewService.getMyRecipeReview(any(GetMyRecipeReviewSDto.class)))
                .willReturn(responseDtoPage);

        // when & then
        mockMvc.perform(get("/api/recipes/reviews/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.data.content[0].cocktail.id").value(CocktailTestFactory.COCKTAIL_ID))
                .andExpect(jsonPath("$.data.content[0].cocktail.name").value(CocktailTestFactory.NAME))
                .andExpect(jsonPath("$.data.content[0].cocktail.description").value(CocktailTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.content[0].cocktail.recipe").value(CocktailTestFactory.RECIPE))
                .andExpect(jsonPath("$.data.content[0].cocktail.type").value(CocktailTestFactory.TYPE.toString()))
                .andExpect(jsonPath("$.data.content[0].cocktail.likeCounts").value(CocktailTestFactory.LIKE_COUNT))
                .andExpect(jsonPath("$.data.content[0].cocktail.userId").value(UserTestFactory.USER_ID))

                .andExpect(jsonPath("$.data.content[0].user.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.content[0].user.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.content[0].user.nickName").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.content[0].user.userRole").value(UserTestFactory.ROLE_USER.toString()))

                .andExpect(jsonPath("$.data.content[0].reviewId").value(RecipeReviewTestFactory.RECIPE_REVIEW_ID))
                .andExpect(jsonPath("$.data.content[0].star").value(RecipeReviewTestFactory.STAR.toString()))
                .andExpect(jsonPath("$.data.content[0].content").value(RecipeReviewTestFactory.CONTENT))
                .andReturn();
    }

    @DisplayName("레시피 리뷰 단건 조회 성공")
    @Test
    void successGetRecipeReview() throws Exception {
        // given
        RecipeReviewResponseDto responseDto = RecipeReviewTestFactory.createRecipeReviewResponseDto();
        given(recipeReviewService.getRecipeReview(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/recipes/reviews/" + RecipeReviewTestFactory.RECIPE_REVIEW_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.data.cocktail.id").value(CocktailTestFactory.COCKTAIL_ID))
                .andExpect(jsonPath("$.data.cocktail.name").value(CocktailTestFactory.NAME))
                .andExpect(jsonPath("$.data.cocktail.description").value(CocktailTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.cocktail.recipe").value(CocktailTestFactory.RECIPE))
                .andExpect(jsonPath("$.data.cocktail.type").value(CocktailTestFactory.TYPE.toString()))
                .andExpect(jsonPath("$.data.cocktail.likeCounts").value(CocktailTestFactory.LIKE_COUNT))
                .andExpect(jsonPath("$.data.cocktail.userId").value(UserTestFactory.USER_ID))

                .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))

                .andExpect(jsonPath("$.data.reviewId").value(RecipeReviewTestFactory.RECIPE_REVIEW_ID))
                .andExpect(jsonPath("$.data.star").value(RecipeReviewTestFactory.STAR.toString()))
                .andExpect(jsonPath("$.data.content").value(RecipeReviewTestFactory.CONTENT))
                .andReturn();
    }

    @DisplayName("레시피 리뷰 전체 조회 성공")
    @Test
    void successGetAllRecipeReview() throws Exception {
        // given
        Page<RecipeReviewResponseDto> responseDtoPage = RecipeReviewTestFactory.createRecipeReviewResponseDtoPage();
        given(recipeReviewService.getAllRecipeReview(any(GetAllRecipeReviewSDto.class)))
                .willReturn(responseDtoPage);

        // when & then
        mockMvc.perform(get("/api/recipes/" + CocktailTestFactory.COCKTAIL_ID + "/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.data.content[0].cocktail.id").value(CocktailTestFactory.COCKTAIL_ID))
                .andExpect(jsonPath("$.data.content[0].cocktail.name").value(CocktailTestFactory.NAME))
                .andExpect(jsonPath("$.data.content[0].cocktail.description").value(CocktailTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.content[0].cocktail.recipe").value(CocktailTestFactory.RECIPE))
                .andExpect(jsonPath("$.data.content[0].cocktail.type").value(CocktailTestFactory.TYPE.toString()))
                .andExpect(jsonPath("$.data.content[0].cocktail.likeCounts").value(CocktailTestFactory.LIKE_COUNT))
                .andExpect(jsonPath("$.data.content[0].cocktail.userId").value(UserTestFactory.USER_ID))

                .andExpect(jsonPath("$.data.content[0].user.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.content[0].user.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.content[0].user.nickName").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.content[0].user.userRole").value(UserTestFactory.ROLE_USER.toString()))

                .andExpect(jsonPath("$.data.content[0].reviewId").value(RecipeReviewTestFactory.RECIPE_REVIEW_ID))
                .andExpect(jsonPath("$.data.content[0].star").value(RecipeReviewTestFactory.STAR.toString()))
                .andExpect(jsonPath("$.data.content[0].content").value(RecipeReviewTestFactory.CONTENT))
                .andReturn();
    }

    @DisplayName("레시피 리뷰 수정 성공")
    @Test
    void successUpdateRecipeReview() throws Exception {
        // given
        UpdateRecipeRequestDto requestDto = RecipeReviewTestFactory.createUpdateRecipeRequestDto();
        RecipeReviewResponseDto responseDto = RecipeReviewTestFactory.createRecipeReviewResponseDto();
        given(recipeReviewService.updateRecipeReview(any(UpdateRecipeReviewSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/recipes/reviews/" + RecipeReviewTestFactory.RECIPE_REVIEW_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.data.cocktail.id").value(CocktailTestFactory.COCKTAIL_ID))
                .andExpect(jsonPath("$.data.cocktail.name").value(CocktailTestFactory.NAME))
                .andExpect(jsonPath("$.data.cocktail.description").value(CocktailTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.cocktail.recipe").value(CocktailTestFactory.RECIPE))
                .andExpect(jsonPath("$.data.cocktail.type").value(CocktailTestFactory.TYPE.toString()))
                .andExpect(jsonPath("$.data.cocktail.likeCounts").value(CocktailTestFactory.LIKE_COUNT))
                .andExpect(jsonPath("$.data.cocktail.userId").value(UserTestFactory.USER_ID))

                .andExpect(jsonPath("$.data.user.id").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.user.email").value(UserTestFactory.EMAIL))
                .andExpect(jsonPath("$.data.user.nickName").value(UserTestFactory.NICKNAME))
                .andExpect(jsonPath("$.data.user.userRole").value(UserTestFactory.ROLE_USER.toString()))

                .andExpect(jsonPath("$.data.reviewId").value(RecipeReviewTestFactory.RECIPE_REVIEW_ID))
                .andExpect(jsonPath("$.data.star").value(RecipeReviewTestFactory.STAR.toString()))
                .andExpect(jsonPath("$.data.content").value(RecipeReviewTestFactory.CONTENT))
                .andReturn();
    }

    @DisplayName("레시피 리뷰 제거 성공")
    @Test
    void successDeleteRecipeReview() throws Exception {
        // given

        // when & then
        mockMvc.perform(delete("/api/recipes/reviews/" + RecipeReviewTestFactory.RECIPE_REVIEW_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_RCP_RVW_SUCCESS))
                .andReturn();
    }
}