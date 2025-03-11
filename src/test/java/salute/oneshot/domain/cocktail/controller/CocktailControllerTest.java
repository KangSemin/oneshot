package salute.oneshot.domain.cocktail.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippet;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
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
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.CreateCocktailRequestDto;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.global.util.S3Util;
import salute.oneshot.util.CocktailTestFactory;
import salute.oneshot.util.UserTestFactory;


@WebMvcTest(CocktailController.class)
class CocktailControllerTest extends AbstractRestDocsTests {

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

        MockMultipartFile requestPart = new MockMultipartFile(
            "request",
            "",
            MediaType.APPLICATION_JSON_VALUE,
            objectMapper.writeValueAsString(request).getBytes()
        );
//        given(cocktailService.createCocktail(any(CreateCocktailSDto.class)))

        // when & then
        mockMvc.perform(multipart("/api/cocktails")
                .file(imageFile)
                .file(requestPart)
//                .content(objectMapper.writeValueAsString(request))
                .with(user(UserTestFactory.createMockUserDetails()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    @DisplayName("칵테일 상세 조회")
    @WithMockUser
    void getCocktailById() throws Exception {

        // given
        given(cocktailService.getCocktail(1L))
            .willReturn(CocktailResponseDto.from(CocktailTestFactory.createBlackRussian()));

        // when & then
        mockMvc.perform(get("/api/cocktails/{cocktailId}", 1)
                .with(user(UserTestFactory.createMockUserDetails()))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
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
                .param("page", "1")
                .param("size", "10")
                .param("keyword", "러시안")
                .param("recipeType", "OFFICIAL")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
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
            .andExpect(status().isOk());

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
            .andExpect(status().isOk());

    }

}