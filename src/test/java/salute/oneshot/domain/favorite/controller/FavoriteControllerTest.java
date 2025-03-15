package salute.oneshot.domain.favorite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.favorite.dto.response.FavoritePageResponseDto;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.response.GetFavoriteStatusDto;
import salute.oneshot.domain.favorite.dto.service.DeleteFavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.FavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.GetFavoritesSDto;
import salute.oneshot.domain.favorite.entity.Favorite;
import salute.oneshot.domain.favorite.service.FavoriteService;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.CocktailTestFactory;
import salute.oneshot.util.FavoriteTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FavoriteController.class)
@Import(TestSecurityConfig.class)
class FavoriteControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FavoriteService favoriteService;

    private Favorite favorite;
    private Cocktail cocktail;

    @BeforeEach
    void setUp() {
        cocktail = CocktailTestFactory.createBlackRussian();
        favorite = FavoriteTestFactory.createFavorite(cocktail);
    }

    @DisplayName("즐겨찾기 등록 성공")
    @Test
    void successCreateFavorite() throws Exception {
        // given
        FavoriteResponseDto responseDto =
                FavoriteResponseDto.from(cocktail, favorite);

        given(favoriteService.createFavorite(any(FavoriteSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/cocktails/{cocktailId}/favorites", cocktail.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_FVRT_SUCCESS))
                .andExpect(jsonPath("$.data.cocktailId").value(cocktail.getId()))
                .andExpect(jsonPath("$.data.name").value(cocktail.getName()))
                .andExpect(jsonPath("$.data.description").value(cocktail.getDescription()))
                .andExpect(jsonPath("$.data.type").value(cocktail.getType().toString()))
                .andExpect(jsonPath("$.data.favoritedAt").value(org.hamcrest.Matchers.startsWith("2025-03-10T01:01")))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite-controller-test/success-create-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_CREATE_API")))
                .andReturn();
    }

    @DisplayName("즐겨찾기 등록 실패: 이미 등록된 즐겨찾기")
    @Test
    void duplicatedFavoriteCreateFavorite() throws Exception {
        // given
        given(favoriteService.createFavorite(any(FavoriteSDto.class)))
                .willThrow(new ConflictException(ErrorCode.DUPLICATE_FAVORITE));

        // when & then
        mockMvc.perform(post("/api/cocktails/{cocktailId}/favorites", cocktail.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DUPLICATE_FAVORITE.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/duplicated-favorite-create-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_CREATE_API")))
                .andReturn();
    }

    @DisplayName("즐겨찾기 등록 실패: 존재하지 않는 칵테일 아이디로 등록 시도")
    @Test
    void invalidFavoriteIdCreateFavorite() throws Exception {
        // given
        given(favoriteService.createFavorite(any(FavoriteSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));

        // when & then
        mockMvc.perform(post("/api/cocktails/{cocktailId}/favorites", cocktail.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.COCKTAIL_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/invalid-favorite-id-create-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_CREATE_API")))
                .andReturn();
    }

    @DisplayName("즐겨찾기 여부 조회 성공")
    @Test
    void successCheckFavorite() throws Exception {
        // given
        GetFavoriteStatusDto responseDto =
                GetFavoriteStatusDto.of(true);
        given(favoriteService.checkFavorite(any(FavoriteSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/cocktails/{cocktailId}/favorites", cocktail.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_FVRT_STAT_SUCCESS))
                .andExpect(jsonPath("$.data.favorited").value(true))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/success-check-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_CHECK_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_CHECK_API")))
                .andReturn();
    }

    @DisplayName("즐겨찾기 목록 조회 성공")
    @Test
    void successGetFavorites() throws Exception {
        // given
        List<FavoriteResponseDto> favoritePage = List.of(FavoriteResponseDto.from(favorite.getCocktail(), favorite));
        PageImpl<FavoriteResponseDto> page = new PageImpl<>(favoritePage);
        FavoritePageResponseDto responseDto = FavoritePageResponseDto.from(page);

        given(favoriteService.getFavorites(any(GetFavoritesSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/favorites")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_FVRT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.favorites").isArray())
                .andExpect(jsonPath("$.data.favorites[0].cocktailId").value(cocktail.getId()))
                .andExpect(jsonPath("$.data.favorites[0].name").value(cocktail.getName()))
                .andExpect(jsonPath("$.data.favorites[0].description").value(cocktail.getDescription()))
                .andExpect(jsonPath("$.data.favorites[0].type").value(cocktail.getType().toString()))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/success-get-favorites",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_LIST_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("즐겨찾기 목록 조회 성공: 빈 목록 조회")
    @Test
    void successGetEmptyFavorites() throws Exception {
        // given
        List<FavoriteResponseDto> emptyFavoritePage = List.of();
        PageImpl<FavoriteResponseDto> emptyPage = new PageImpl<>(emptyFavoritePage);
        FavoritePageResponseDto responseDto = FavoritePageResponseDto.from(emptyPage);

        given(favoriteService.getFavorites(any(GetFavoritesSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/favorites")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.favorites").isArray())
                .andExpect(jsonPath("$.data.favorites").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/success-get-empty-favorites",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_LIST_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_LIST_API"),
                        ApiDocumentFactory.PAGE_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("즐겨찾기 삭제 성공")
    @Test
    void successDeleteFavorite() throws Exception {
        // given
        FavoriteResponseDto responseDto =
                FavoriteResponseDto.from(cocktail, favorite);

        given(favoriteService.deleteFavorite(any(DeleteFavoriteSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(delete("/api/favorites/{favoriteId}", favorite.getId())
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_FVRT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.cocktailId").value(cocktail.getId()))
                .andExpect(jsonPath("$.data.name").value(cocktail.getName()))
                .andExpect(jsonPath("$.data.description").value(cocktail.getDescription()))
                .andExpect(jsonPath("$.data.type").value(cocktail.getType().toString()))
                .andExpect(jsonPath("$.data.favoritedAt").value(org.hamcrest.Matchers.startsWith("2025-03-10T01:01")))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/success-delete-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_DELETE_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_DELETE_API")))
                .andReturn();
    }

    @DisplayName("즐겨찾기 삭제 실패: 존재하지 않는 즐겨찾기아이디로 삭제 시도")
    @Test
    void invalidIdDeleteFavorite() throws Exception {
        // given
        given(favoriteService.deleteFavorite(any(DeleteFavoriteSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.FORBIDDEN_ACCESS));

        // when & then
        mockMvc.perform(delete("/api/favorites/{favoriteId}", favorite.getId())
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.FORBIDDEN_ACCESS.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "favorite/invalid-id-delete-favorite",
                        ApiDocumentFactory.FAVORITE_TAG,
                        ApiDocumentationLoader.getSummary("favorite", "FAVORITE_DELETE_API"),
                        ApiDocumentationLoader.getDescription("event", "FAVORITE_DELETE_API")))
                .andReturn();
    }
}

