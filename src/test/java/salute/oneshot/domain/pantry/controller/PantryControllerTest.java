package salute.oneshot.domain.pantry.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.pantry.dto.response.PantryResponseDto;
import salute.oneshot.domain.pantry.dto.service.AddIngrToPantrySDto;
import salute.oneshot.domain.pantry.dto.service.RemoveIngrFromPantrySDto;
import salute.oneshot.domain.pantry.entity.Pantry;
import salute.oneshot.domain.pantry.service.PantryService;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.PantryTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@Import(TestSecurityConfig.class)
@WebMvcTest(PantryController.class)
class PantryControllerTest extends AbstractRestDocsTests {

    @MockitoBean
    PantryService pantryService;

    Long userId = 1L;
    Long ingredientId = 1L;
    Pantry pantry = PantryTestFactory.createPantry();
    PantryResponseDto responseDto = PantryResponseDto.from(pantry);


    @Test
    void 펜트리_재료추가_성공() throws Exception {
        AddIngrToPantrySDto sDto = AddIngrToPantrySDto.of(userId, ingredientId);


        when(pantryService.addIngredientToPantry(any(AddIngrToPantrySDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/pantries/ingredients/{ingredientId}", 1L)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)) // 위치 수정
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_PNTR_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.userId").value(responseDto.getUserId()))
                .andExpect(jsonPath("$.data.pantryId").value(responseDto.getPantryId()))
                .andDo(document("pantry/addIngrToPantry",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("pantry")
                                .summary("펜트리에 재료 추가 API")
                                .description("사용자의 펜트리에 특정 재료를 추가하는 API")
                                .pathParameters(
                                        parameterWithName("ingredientId").description("추가할 재료의 ID")
                                )
                                .build()
                        )
                ));

    }

    @Test
    @DisplayName("재료추가 실패_재료 미존재")
    void addIngrToPantry_failedCase1() throws Exception {

        AddIngrToPantrySDto sDto = AddIngrToPantrySDto.of(userId, ingredientId);


        when(pantryService.addIngredientToPantry(any(AddIngrToPantrySDto.class)))
                .thenThrow(new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));

        mockMvc.perform(post("/api/pantries/ingredients/{ingredientId}", 1L)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INGREDIENT_NOT_FOUND.getMessage()))
                .andDo(document("pantry/addIngrToPantry_failedCase1",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("pantry")
                                .summary("펜트리 재료 추가 실패")
                                .description("펜트리 재료 추가 실패 - 해당 재료가 존재하지 않는 경우")
                                .pathParameters(
                                        parameterWithName("ingredientId").description("추가하려는 재료의 ID (존재하지 않는 경우 예외 발생)")
                                )
                                .build()
                        )
                ));

    }

    @Test
    @DisplayName("재료추가 실패_재료 중복 추가 시도")
    void addIngrToPantry_failedCase2() throws Exception {

        AddIngrToPantrySDto sDto = AddIngrToPantrySDto.of(userId, ingredientId);


        when(pantryService.addIngredientToPantry(any(AddIngrToPantrySDto.class)))
                .thenThrow(new NotFoundException(ErrorCode.DUPLICATE_INGREDIENT));

        mockMvc.perform(post("/api/pantries/ingredients/{ingredientId}", 1L)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_INGREDIENT.getMessage()))
                .andDo(document("pantry/addIngrToPantry_failedCase2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("pantry")
                                .summary("펜트리 재료 추가 실패")
                                .description("펜트리 재료 추가 실패 - 이미 추가된 재료")
                                .pathParameters(
                                        parameterWithName("ingredientId").description("추가할 재료의 ID (이미 추가된 경우 예외 발생)")
                                )
                                .build()
                        )
                ));

    }


    @Test
    void getMyPantry() throws Exception {
        List<PantryResponseDto> pantryResponseDtoList = List.of(responseDto);
        when(pantryService.getPantry(any(Long.class))).thenReturn(pantryResponseDtoList);

        mockMvc.perform(get("/api/pantries")
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_PNTR_SUCCESS))
                .andExpect(jsonPath("$.data[0].userId").value(pantryResponseDtoList.get(0).getUserId()))
                .andExpect(jsonPath("$.data[0].pantryId").value(pantryResponseDtoList.get(0).getPantryId()))
                .andDo(document("pantry/getMyPantry",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("pantry")
                                .summary("나의 펜트리 조회 API")
                                .description("로그인한 사용자의 펜트리 목록을 조회하는 API")
                                .build()
                        )
                ));

    }

    @Test
    void clearPantryIngredients() throws Exception {

        doNothing().when(pantryService).clearPantryIngredients(any(Long.class));

        mockMvc.perform(delete("/api/pantries")
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_PNTR_SUCCESS))
                .andDo(document("pantry/clearPantryIngredients",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("pantry")
                                .summary("펜트리 비우기 API")
                                .description("사용자의 펜트리에 있는 모든 재료를 삭제하는 API")
                                .build()
                        )
                ));

    }

    @Test
    void removeIngredient() throws Exception {
        RemoveIngrFromPantrySDto sDto = RemoveIngrFromPantrySDto.of(userId, List.of(ingredientId));

        doNothing().when(pantryService).removeIngredientsFromPantry(any(RemoveIngrFromPantrySDto.class));

        mockMvc.perform(delete("/api/pantries/ingredients")
                        .queryParam("ingredientIds", "1", "2", "3")
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_INGR_SUCCESS))
                .andDo(document("pantry/removeIngredient",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .summary("펜트리 내의 재료 삭제 API")
                                .description("사용자의 펜트리에서 재료를 삭제하는 API")
                                .tag("pantry")
                                .queryParameters(
                                        parameterWithName("ingredientIds").description("삭제할 재료의 ID 리스트 "))
                                .build()
                        )
                ));
    }
}