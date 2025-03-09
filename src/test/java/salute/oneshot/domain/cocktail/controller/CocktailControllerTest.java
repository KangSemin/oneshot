package salute.oneshot.domain.cocktail.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;


@WebMvcTest(CocktailController.class)
class CocktailControllerTest extends AbstractRestDocsTests {

    @MockitoBean
    private CocktailService cocktailService;



    @Test
    @DisplayName("칵테일 목록 조회")
    @WithMockUser
    void getCocktails() throws Exception {
        // given
        CocktailResponseDto cocktailResponse = createCocktailResponse();
        given(cocktailService.searchByCondition(any()))
            .willReturn(new PageImpl<>(List.of(cocktailResponse)));

        // when & then
        mockMvc.perform(get("/api/cocktails")
                .param("page", "1")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                queryParameters(
                    parameterWithName("page").description("페이지 번호 (기본값: 1)"),
                    parameterWithName("size").description("페이지 크기 (기본값: 10)"),
                    parameterWithName("keyword").description("검색 키워드").optional(),
                    parameterWithName("recipeType").description("레시피 타입").optional()
                ),
                responseFields(
                    fieldWithPath("success").description("성공 여부"),
                    fieldWithPath("message").description("응답 메시지"),
                    fieldWithPath("data.content[]").description("칵테일 목록"),
                    fieldWithPath("data.content[].id").description("칵테일 ID"),
                    fieldWithPath("data.content[].name").description("칵테일 이름"),
                    fieldWithPath("data.content[].description").description("칵테일 설명"),
                    fieldWithPath("data.content[].recipe").description("레시피"),
                    fieldWithPath("data.content[].type").description("레시피 타입"),
                    fieldWithPath("data.content[].imageUrl").description("이미지 URL").optional(),
                    fieldWithPath("data.content[].ingredientList[]").description("재료 목록"),
                    fieldWithPath("data.content[].ingredientList[].id").description("재료 ID"),
                    fieldWithPath("data.content[].ingredientList[].name").description("재료 이름"),
                    fieldWithPath("data.content[].ingredientList[].category").description("재료 카테고리"),
                    fieldWithPath("data.content[].ingredientList[].avb").description("알코올 도수"),
                    fieldWithPath("data.pageable").description("페이징 정보"),
                    fieldWithPath("data.totalElements").description("총 요소 수"),
                    fieldWithPath("data.totalPages").description("총 페이지 수")
                )
            ));
    }

    @Test
    @DisplayName("칵테일 상세 조회")
    @WithMockUser
    void getCocktail() throws Exception {
        // given
        CocktailResponseDto cocktailResponse = createCocktailResponse();
        given(cocktailService.getCocktail(any()))
            .willReturn(cocktailResponse);

        // when & then
        mockMvc.perform(get("/api/cocktails/{cocktailId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                pathParameters(
                    parameterWithName("cocktailId").description("칵테일 ID")
                ),
                responseFields(
                    fieldWithPath("success").description("성공 여부"),
                    fieldWithPath("message").description("응답 메시지"),
                    fieldWithPath("data.id").description("칵테일 ID"),
                    fieldWithPath("data.name").description("칵테일 이름"),
                    fieldWithPath("data.description").description("칵테일 설명"),
                    fieldWithPath("data.recipe").description("레시피"),
                    fieldWithPath("data.type").description("레시피 타입"),
                    fieldWithPath("data.imageUrl").description("이미지 URL").optional(),
                    fieldWithPath("data.ingredientList[]").description("재료 목록"),
                    fieldWithPath("data.ingredientList[].id").description("재료 ID"),
                    fieldWithPath("data.ingredientList[].name").description("재료 이름"),
                    fieldWithPath("data.ingredientList[].category").description("재료 카테고리"),
                    fieldWithPath("data.ingredientList[].avb").description("알코올 도수")
                )
            ));
    }

    private CocktailResponseDto createCocktailResponse() {
        IngrResponseDto ingredient = new IngrResponseDto(
            1L, "진", "진 설명", IngredientCategory.JIN, 40.0
        );

        UserResponseDto user = new UserResponseDto(1L, "user1", "user1@test.com");

        return new CocktailResponseDto(
            1L,
            "진토닉",
            "진토닉 설명",
            "1. 진을 넣는다\n2. 토닉워터를 넣는다",
            RecipeType.OFFICIAL,
            user,
            List.of(ingredient),
            0,
            0.0,
            0,
            null,
            null
        );
    }
}
}