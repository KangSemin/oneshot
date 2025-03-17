package salute.oneshot.domain.ingredient.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.SearchIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.service.IngredientService;
import salute.oneshot.util.IngredientTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.lang.reflect.Constructor;

import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IngredientController.class)
class IngredientControllerTest extends AbstractRestDocsTests {


    @MockitoBean
    IngredientService ingredientService;

    @Autowired
    ObjectMapper objectMapper;


    private final String  API_TAG = "Ingredient_API";
    private Ingredient ingredient = IngredientTestFactory.createVodka();
    private Long ingrId = IngredientTestFactory.INGREDIENT_ID;

    MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "test.jpg",
            MediaType.IMAGE_JPEG_VALUE, new byte[0]);


    @Test
    @WithMockUser
    void 재료생성_성공() throws Exception {

        Constructor<CreateIngrRequestDto> createConst =
                CreateIngrRequestDto.class.getDeclaredConstructor(
                        String.class, String.class, String.class, Double.class);

        createConst.setAccessible(true);

        CreateIngrRequestDto requestDto = createConst.newInstance("보드카", "보드카", "VODKA", 40.0d);

        byte[] jsonRequest = objectMapper.writeValueAsBytes(requestDto);

        MockMultipartFile requestFile =
                new MockMultipartFile("request", "test.json", MediaType.APPLICATION_JSON_VALUE, jsonRequest);


        IngrResponseDto responseDto = IngrResponseDto.from(ingredient);

        given(ingredientService.createIngredient(any(CreateIngrSDto.class))).willReturn(responseDto);


        mockMvc.perform(multipart("/api/ingredients")
                        .file(multipartFile)
                        .file(requestFile)
                        .with(user(UserTestFactory.createMockAdminDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId()))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.category").value(responseDto.getCategory().name()))
                .andExpect(jsonPath("$.data.avb").value(responseDto.getAVB()))
                .andDo(document("ingredient/create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 생성 성공")
                                .description("재료를 생성하는 API, 재료 생성에 성공")
                                .build()
                        )
                ));


    }

    @Test
    @WithMockUser
    void 재료생성_실패_일반유저_시도() throws Exception {

        Constructor<CreateIngrRequestDto> createConst =
                CreateIngrRequestDto.class.getDeclaredConstructor(
                        String.class, String.class, String.class, Double.class);

        createConst.setAccessible(true);

        CreateIngrRequestDto requestDto = createConst.newInstance("보드카", "보드카", "VODKA", 40.0d);

        byte[] jsonRequest = objectMapper.writeValueAsBytes(requestDto);

        MockMultipartFile requestFile =
                new MockMultipartFile("request", "test.json", MediaType.APPLICATION_JSON_VALUE, jsonRequest);


        IngrResponseDto responseDto = IngrResponseDto.from(ingredient);

        given(ingredientService.createIngredient(any(CreateIngrSDto.class))).willReturn(responseDto);


        mockMvc.perform(multipart("/api/ingredients")
                        .file(multipartFile)
                        .file(requestFile)
                        .with(user(UserTestFactory.createMockUserDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(document("ingredient/create-failedCase1",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 생성 살패 case1 ")
                                .description("재료를 생성하는 API로, 일반유저 권한 없음으로 실패.")
                                .build()
                        )
                ));
    }


    @Test
    @WithMockUser
    public void 재료생성_실패_이미지업로드_실패() throws Exception {
        Constructor<CreateIngrRequestDto> createConst =
                CreateIngrRequestDto.class.getDeclaredConstructor(
                        String.class, String.class, String.class, Double.class);

        createConst.setAccessible(true);

        CreateIngrRequestDto requestDto = createConst.newInstance("보드카", "보드카", "VODKA", 40.0d);

        byte[] jsonRequest = objectMapper.writeValueAsBytes(requestDto);

        MockMultipartFile requestFile =
                new MockMultipartFile("request", "test.json", MediaType.APPLICATION_JSON_VALUE, jsonRequest);


        MockMultipartFile multipartFile =
                new MockMultipartFile("imageFile", "test.txt", MediaType.TEXT_HTML_VALUE, new byte[1]);


        mockMvc.perform(multipart("/api/ingredients")
                        .file(multipartFile)
                        .file(requestFile)
                        .with(user(UserTestFactory.createMockAdminDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(document("ingredient/create-failedCase2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 생성 실패 case2")
                                .description(" 재료를 생성하는 API, 이미지 업로드에 실패로 재료 생성 실패.")
                                .build()
                        )
                ));

    }


    @Test
    void 재료_단건조회_성공() throws Exception {

        IngrResponseDto responseDto = IngrResponseDto.from(ingredient);

        given(ingredientService.getIngredient(any(Long.class))).willReturn(responseDto);

        mockMvc.perform(get("/api/ingredients/{ingredientId}", ingrId))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId()))
                .andDo(document("ingredient/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 단건 조회 성공 ")
                                .description("재료 단건 조회에 성공합니다.")
                                .pathParameters(
                                        parameterWithName("ingredientId").type(SimpleType.INTEGER).description("조회할 재료 id")

                                ).build())
                ));

    }


    @Test
    void 조건별_재료조회_성공() throws Exception {

        Page<IngrResponseDto> responseDtoPage = new PageImpl<>(
                List.of(
                        IngrResponseDto.from(IngredientTestFactory.createGoldenKeyword()),
                        IngrResponseDto.from(IngredientTestFactory.createGoldenKeyword2())
                ));

        given(ingredientService.searchByCondition(any(SearchIngrSDto.class))).willReturn(responseDtoPage);


        mockMvc.perform(get("/api/ingredients/search")
                        .queryParam("keyword", "golden")
                        .queryParam("category", " ")
                        .queryParam("page", "1")
                        .queryParam("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_LIST_SUCCESS))
                .andDo(document("ingredient/search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .summary("재료 검색 성공")
                                .description("사용자가 검색 키워드와 카테고리를 입력하여 재료를 검색할 수 있는 API")
                                .tag(API_TAG)
                                .queryParameters(
                                        parameterWithName("keyword").defaultValue(" ").description("검색할 재료 키워드(선택)"),
                                        parameterWithName("category").defaultValue(" ").description("재료 카테고리(선택)"),
                                        parameterWithName("page").defaultValue(1).type(SimpleType.INTEGER).description("페이지 번호 (1부터 시작, 선택)"),
                                        parameterWithName("size").defaultValue(10).type(SimpleType.INTEGER).description("한 페이지당 조회할 개수(선택)")
                                )
                                .build())));
    }


    @Test
    void 재료수정_성공() throws Exception {
        UpdateIngrRequestDto requestDto = IngredientTestFactory.updateIngrRequestDto();

        byte[] jsonRequest = objectMapper.writeValueAsBytes(requestDto);

        MockMultipartFile requestFile =
                new MockMultipartFile("request", "test.json", MediaType.APPLICATION_JSON_VALUE, jsonRequest);

        IngredientCategory category = IngredientCategory.valueOf(requestDto.getCategory());

        UpdateIngrSDto sDto = UpdateIngrSDto.of(ingrId, requestDto.getName(), requestDto.getDescription(),
                category, requestDto.getAvb(), multipartFile);//mockMulti

        Ingredient updateIngr = Ingredient.of(sDto.getName(), sDto.getDescription(), sDto.getCategory(), sDto.getAvb(), "url");
        ReflectionTestUtils.setField(updateIngr, "id", sDto.getId());


        IngrResponseDto responseDto = IngrResponseDto.from(updateIngr);

        given(ingredientService.updateIngredient(any(UpdateIngrSDto.class))).willReturn(responseDto);

        mockMvc.perform(multipart(HttpMethod.PATCH, "/api/ingredients/{ingredientId}", ingrId)
                        .file(multipartFile)
                        .file(requestFile)
                        .with(user(UserTestFactory.createMockAdminDetails()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.description").value(responseDto.getDescription()))
                .andExpect(jsonPath("$.data.category").value(responseDto.getCategory().name()))
                .andExpect(jsonPath("$.data.avb").value(responseDto.getAVB()))
                .andExpect(jsonPath("$.data.imageUrl").value(responseDto.getImageUrl()))
                .andDo(document("ingredient/update",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 수정 성공")
                                .description("재료 수정 성공")
                                .pathParameters(
                                        parameterWithName("ingredientId").type(SimpleType.INTEGER).description("수정 할 재료 id"))
                                .build())
                ));
    }


    @Test
    @WithMockUser
    void 재료삭제_성공() throws Exception {

        doNothing().when(ingredientService).deleteIngredient(any(Long.class));

        mockMvc.perform(delete("/api/ingredients/{ingredientId}", ingrId)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_INGR_SUCCESS))
                .andDo(document("ingredient/delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("재료 삭제 성공")
                                .description("재료 삭제 성공")
                                .pathParameters(
                                        parameterWithName("ingredientId").type(SimpleType.INTEGER).description("삭제할 재료 id"))
                                .build()
                        )));
    }
}