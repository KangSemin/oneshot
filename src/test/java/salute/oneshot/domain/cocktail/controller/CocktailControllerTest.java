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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
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

    @Test
    @DisplayName("칵테일 목록 조회")
    @WithMockUser
    void getCocktails() throws Exception {
        // given

        given(cocktailService.getIngrByCondition(any()))
            .willReturn(new PageImpl<>(
                    List.of(
                        CocktailResponseDto.from(CocktailTestFactory.createBlackRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createWhiteRussian()),
                        CocktailResponseDto.from(CocktailTestFactory.createKahluaMilk())
                    )
                )
            );

        // when & then
        mockMvc.perform(get("/api/cocktails")
                .param("page", "1")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

}