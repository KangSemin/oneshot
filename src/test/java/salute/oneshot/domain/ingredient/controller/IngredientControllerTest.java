package salute.oneshot.domain.ingredient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
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
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.util.S3Util;
import salute.oneshot.util.IngredientTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IngredientController.class)
@Import({TestSecurityConfig.class})
class IngredientControllerTest extends AbstractRestDocsTests {


    @MockitoBean
    IngredientService ingredientService;

    @Autowired
    ObjectMapper objectMapper;


    Ingredient ingredient = IngredientTestFactory.createVodka();

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
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId()))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.category").value(responseDto.getCategory().name()))
                .andExpect(jsonPath("$.data.avb").value(responseDto.getAVB()));
    }

//    @Test
//    @WithMockUser
//    void 재료생성_실패_일반유저_시도() throws Exception {
//
//        Constructor<CreateIngrRequestDto> createConst =
//                CreateIngrRequestDto.class.getDeclaredConstructor(
//                        String.class, String.class, String.class, Double.class);
//
//        createConst.setAccessible(true);
//
//        CreateIngrRequestDto requestDto = createConst.newInstance("보드카", "보드카", "VODKA", 40.0d);
//
//
//
//        IngrResponseDto responseDto = IngrResponseDto.from(ingredient);
//
//        given(ingredientService.createIngredient(any(CreateIngrSDto.class))).willReturn(responseDto);
//
//
//        mockMvc.perform(multipart("/api/ingredients")
//                        .file(multipartFile)
//                        .with(user(UserTestFactory.createMockAdminDetails()))
//                        .content(objectMapper.writeValueAsString(requestDto))
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_INGR_SUCCESS))
//                .andExpect(jsonPath("$.data.id").value(responseDto.getId()))
//                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
//                .andExpect(jsonPath("$.data.category").value(responseDto.getCategory().name()))
//                .andExpect(jsonPath("$.data.avb").value(responseDto.getAVB()));
//    }




    @Test
    public void 재료생성_실패_CASE_이미지업로드_실패() throws Exception{
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
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }



    @Test
    void 재료_단건조회_성공() throws Exception {

        IngrResponseDto responseDto = IngrResponseDto.from(ingredient);

        given(ingredientService.getIngredient(any(Long.class))).willReturn(responseDto);

        mockMvc.perform(get("/api/ingredients/{ingredientId}", 1))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(responseDto.getId()));

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
                        .param("keyword", "golden")
                        .param("category", " ")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_INGR_LIST_SUCCESS)

                       );
    }

    @Test
    void 재료수정_성공() throws Exception {
        Constructor<UpdateIngrRequestDto> updateConst =
                UpdateIngrRequestDto.class.getDeclaredConstructor(String.class, String.class, String.class, Double.class);

        updateConst.setAccessible(true);

        UpdateIngrRequestDto requestDto = updateConst.newInstance("보드카", "보드카", "VODKA", 40.0d);

        byte[] jsonRequest = objectMapper.writeValueAsBytes(requestDto);

        MockMultipartFile requestFile = new MockMultipartFile("request", "test.json", MediaType.APPLICATION_JSON_VALUE, jsonRequest);

        IngredientCategory category = IngredientCategory.valueOf(requestDto.getCategory());

        UpdateIngrSDto sDto = UpdateIngrSDto.of(1L, requestDto.getName(), requestDto.getDescription(),
                category, requestDto.getAvb(), multipartFile);//mockMulti

        Ingredient updateIngr = Ingredient.of(sDto.getName(), sDto.getDescription(), sDto.getCategory(), sDto.getAvb(), "url");

        IngrResponseDto responseDto = IngrResponseDto.from(updateIngr);

        given(ingredientService.updateIngredient(any(UpdateIngrSDto.class))).willReturn(responseDto);

        mockMvc.perform(multipart("/api/ingredients/{ingredientId}", 1L)
                        .file(multipartFile)
                        .file(requestFile)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.setMethod("PATCH"); // PATCH 요청으로 변경
                            return request;
                        })
                )
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_INGR_SUCCESS))
                .andExpect(jsonPath("$.data.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.data.description").value(responseDto.getDescription()))
                .andExpect(jsonPath("$.data.category").value(responseDto.getCategory().name()))
                .andExpect(jsonPath("$.data.avb").value(responseDto.getAVB()))
                .andExpect(jsonPath("$.data.imageUrl").value(responseDto.getImageUrl()));
    }


    @Test
    void 재료삭제_성공() throws Exception{

        doNothing().when(ingredientService).deleteIngredient(any(Long.class));

        mockMvc.perform(delete("/api/ingredients/{ingreientId}", 1L))
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_INGR_SUCCESS));
    }
}