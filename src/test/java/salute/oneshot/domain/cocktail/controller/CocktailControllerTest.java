package salute.oneshot.domain.cocktail.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.MultiValueMap;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.request.UpdateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.UpdateCocktailSDto;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.util.S3Util;
import salute.oneshot.util.CocktailTestFactory;
import salute.oneshot.util.UserTestFactory;


@WebMvcTest(CocktailController.class)
class CocktailControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Cocktail API";


    @MockitoBean
    private CocktailService cocktailService;

    @MockitoBean
    private S3Util s3Util;
    @Autowired
    private ObjectMapper objectMapper;

    MockMultipartFile imageFile = new MockMultipartFile(
        "imageFile",
        "test.jpg",
        MediaType.IMAGE_JPEG_VALUE,
        "test image content".getBytes()
    );


    @Test
    @DisplayName("칵테일 생성")
    @WithMockUser
    void createCocktail() throws Exception {

        //given
        Constructor<IngredientRequestDto> ingrConst = IngredientRequestDto.class
            .getDeclaredConstructor(Long.class, String.class);
        ingrConst.setAccessible(true);

        Constructor<CreateCocktailRequestDto> cocktailConst = CreateCocktailRequestDto.class
            .getDeclaredConstructor(String.class, String.class, String.class, List.class);
        cocktailConst.setAccessible(true);

        IngredientRequestDto ingrRequest1 = ingrConst.newInstance(1L, "60ml");
        IngredientRequestDto ingrRequest2 = ingrConst.newInstance(4L, "20ml");

        CreateCocktailRequestDto request = cocktailConst.newInstance(
            "블랙 러시안","보드카와 깔루아로 만드는 칵테일",
            "1.칠링한 온더락 글라스에 재료들을 붓는다.\n2.젓는다.",
            List.of(ingrRequest1, ingrRequest2));


        // when & then
        mockMvc.perform(multipart("/api/cocktails")
                .file(imageFile)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(user(UserTestFactory.createMockUserDetails()))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("cocktail/findCocktailsByKeyword",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("칵테일 생성")
                    .build()
                )));

    }

    @Test
    @DisplayName("칵테일 상세 조회")
    @WithMockUser
    void getCocktailById() throws Exception {

        // given
        CocktailResponseDto response = CocktailResponseDto.from(CocktailTestFactory.createBlackRussian());

        given(cocktailService.getCocktail(1L))
            .willReturn(response);

        // when & then
        mockMvc.perform(get("/api/cocktails/{cocktailId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(UserTestFactory.createMockUserDetails()))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("cocktail/getCocktailById",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .build()
                )));
    }




    @Test
    @DisplayName("키워드를 통한 칵테일 검색")
    @WithMockUser
    void findCocktailsByKeyword() throws Exception {
        // given

        given(cocktailService.getIngrByCondition(any()))
            .willReturn(new PageImpl<>(
                    List.of(
                        CocktailResponseDto.from(CocktailTestFactory.createBlackRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createWhiteRussian())
                    )
                )
            );

        // when & then
        mockMvc.perform(get("/api/cocktails")
                .queryParams(
                    MultiValueMap.fromSingleValue(
                        Map.of(
                            "page", "1",
                            "size", "10",
                            "recipeType", "OFFICIAL",
                            "keyword", "러시안"
                        )))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("cocktail/findCocktailsByKeyword",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("키워드를 통한 칵테일 검색")
                    .queryParameters(
                        parameterWithName("page").description("페이지 넘버").optional(),
                        parameterWithName("size").description("페이지당 항목 수").optional(),
                        parameterWithName("recipeType").description("OFFICIAL/CUSTOM").optional(),
                        parameterWithName("keyword").description("검색 키워드").optional())
                    .build()
                )));
    }

    @Test
    @DisplayName("조주 가능한 칵테일 검색")
    @WithMockUser
    void getCraftableCocktail() throws Exception {

        //given
        SearchCocktailByIngrsReqDto request = mock(SearchCocktailByIngrsReqDto.class);
        setField(request, "ingredientIds", List.of(3, 4));

        given(cocktailService.getCocktailsByIngr(any(SearchCocktailSDto.class)))
            .willReturn(new PageImpl<>(
                    List.of(
                        CocktailResponseDto.from(CocktailTestFactory.createBlackRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createWhiteRussian())
                    )
                )
            );

        // when & then
        mockMvc.perform(get("/api/cocktails/search")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .queryParams(
                    MultiValueMap.fromSingleValue(
                        Map.of(
                            "page", "1",
                            "size", "10",
                            "isCraftable", "false",
                            "recipeType", "OFFICIAL"
                        )))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("cocktail/getCraftableCocktail",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("키워드를 통한 칵테일 검색")
                    .queryParameters(
                        parameterWithName("page").description("페이지 넘버").optional(),
                        parameterWithName("size").description("페이지당 항목 수").optional(),
                        parameterWithName("isCraftable").description("조주 가능한 칵테일만 검색").optional(),
                        parameterWithName("recipeType").description("OFFICIAL/CUSTOM").optional())
                    .build()
                )));

    }


    @Test
    @DisplayName("재료를 통한 칵테일 조회")
    @WithMockUser
    void getCocktailByIngr() throws Exception {

        //given
        SearchCocktailByIngrsReqDto request = mock(SearchCocktailByIngrsReqDto.class);
        setField(request, "ingredientIds", List.of(3, 4));

        given(cocktailService.getCocktailsByIngr(any(SearchCocktailSDto.class)))
            .willReturn(new PageImpl<>(
                    List.of(
                        CocktailResponseDto.from(CocktailTestFactory.createBlackRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createWhiteRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createKahluaMilk())
                    )
                )
            );


        // when & then
        mockMvc.perform(get("/api/cocktails/search")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("size", "10")
                .param("isCraftable", "false")
                .param("recipeType", "OFFICIAL")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("cocktail/getCocktailByIngr",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("재료를 통한 칵테일 검색")
                    .queryParameters(
                        parameterWithName("page").description("페이지 넘버").optional(),
                        parameterWithName("size").description("페이지당 항목 수").optional(),
                        parameterWithName("isCraftable").description("조주 가능한 칵테일만 검색").optional(),
                        parameterWithName("recipeType").description("OFFICIAL/CUSTOM").optional())
                    .build()
                )));

    }

    @Test
    @DisplayName("칵테일 수정")
    @WithMockUser
    void updateCocktail() throws Exception {

        //given
        Constructor<IngredientRequestDto> ingrConst = IngredientRequestDto.class
            .getDeclaredConstructor(Long.class, String.class);
        ingrConst.setAccessible(true);

        Constructor<UpdateCocktailRequestDto> cocktailConst = UpdateCocktailRequestDto.class
            .getDeclaredConstructor(String.class, String.class, String.class, List.class);
        cocktailConst.setAccessible(true);

        IngredientRequestDto ingrRequest1 = ingrConst.newInstance(1L, "60ml");
        IngredientRequestDto ingrRequest2 = ingrConst.newInstance(4L, "20ml");

        UpdateCocktailRequestDto request = cocktailConst.newInstance(
            "블랙 러시안","보드카와 깔루아로 만드는 칵테일",
            "1.칠링한 온더락 글라스에 재료들을 붓는다.\n2.젓는다.",
            List.of(ingrRequest1, ingrRequest2));


        CocktailResponseDto response = CocktailResponseDto.from(CocktailTestFactory.createBlackRussian());

        given(cocktailService.updateCocktail(any(UpdateCocktailSDto.class))).willReturn(response);


        // when & then
        mockMvc.perform(patch("/api/cocktails/{cocktailId}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(user(UserTestFactory.createMockUserDetails()))
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_CCKTL_SUCCESS))
            .andExpect(status().isOk())
            .andDo(document("cocktail/updateCocktail",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("칵테일 정보 수정")
                    .build()
                )));
    }

    @Test
    void deleteCocktail() throws Exception{

        mockMvc.perform(delete("/api/cocktails/{cocktailId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .with(user(UserTestFactory.createMockUserDetails())))
            .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_CCKTL_SUCCESS))
            .andDo(document("cocktail/deleteCocktail",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag(API_TAG)
                    .summary("칵테일 삭제")
                    .build()
                )));
    }

}