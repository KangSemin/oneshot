package salute.oneshot.domain.cocktail.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.MultiValueMap;
import salute.oneshot.domain.cocktail.dto.request.SearchCocktailByIngrsReqDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.service.CocktailService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.global.util.S3Util;
import salute.oneshot.util.CocktailTestFactory;


@WebMvcTest(CocktailController.class)
class CocktailControllerTest extends AbstractRestDocsTests {

    @MockitoBean
    private CocktailService cocktailService;

    @MockitoBean
    private S3Util s3Util;
    @Autowired
    private ObjectMapper objectMapper;

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
                .param("keyword","러시안")
                .param("recipeType", "OFFICIAL")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("조주 가능한 칵테일 검색")
    @WithMockUser
    void getCraftableCocktail() throws Exception{

        //given
        SearchCocktailByIngrsReqDto request = mock(SearchCocktailByIngrsReqDto.class);
        ReflectionTestUtils.setField(request,"ingredientIds",List.of(3,4));

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
    void getCocktailByIngr() throws Exception{

        //given
        SearchCocktailByIngrsReqDto request = mock(SearchCocktailByIngrsReqDto.class);
        ReflectionTestUtils.setField(request,"ingredientIds",List.of(3,4));

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
                .param("isCraftable","false")
                .param("recipeType", "OFFICIAL")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

}