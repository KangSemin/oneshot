package salute.oneshot.domain.cocktail.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.CreateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.DeleteCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.UpdateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.findCocktailSDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.CocktailDocument;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.repository.CocktailIngredientRepository;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;
    private final ElasticsearchOperations operations;
    private final ElasticsearchClient client;
    private final RedisService redisService;

    private final String COCKTAIL_INDEX = "cocktails";

    @Transactional
    public void createCocktail(CreateCocktailSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        RecipeType recipeType =
            sDto.getUserRole().equals(UserRole.USER) ? RecipeType.CUSTOM : RecipeType.OFFICIAL;

        Cocktail cocktail = Cocktail.of(sDto.getName(), sDto.getDescription(), sDto.getRecipe(),
            recipeType, user, new ArrayList<>());

        cocktailRepository.save(cocktail);

        List<Long> ingredientIds = sDto.getIngredientList().stream()
            .map(IngredientRequestDto::getIngredientId)
            .toList();

        Map<Long, Ingredient> ingredientMap = ingredientRepository.findAllById(ingredientIds)
            .stream()
            .collect(Collectors.toMap(Ingredient::getId, Function.identity()));

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map(req -> CocktailIngredient.of(cocktail, ingredientMap.get(req.getIngredientId()),
                req.getVolume()))
            .collect(Collectors.toList());

        cocktailIngredientRepository.saveAll(ingredientList);
        operations.save(CocktailDocument.of(cocktail, ingredientMap));
    }

    @Transactional(readOnly = true)
    public Page<CocktailResponseDto> findCocktailsByIngr(SearchCocktailSDto sDto) {

        Pageable pageable = PageRequest.of(sDto.getPage() - 1, sDto.getSize());

        List<Ingredient> ingredientList = ingredientRepository.findAllById(sDto.getIngredientIds());

        Page<Cocktail> cocktailPage = cocktailRepository.searchCocktailsByIngredients(
            ingredientList, pageable);

        return cocktailPage.map(CocktailResponseDto::from);

    }

    @Transactional
    public void deleteCocktail(DeleteCocktailSDto sDto) throws IOException {

        if (!(cocktailRepository.existsByIdAndUserId(sDto.getCocktailId(), sDto.getUserId())
            && sDto.getUserRole().equals(UserRole.ADMIN))) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }

        DeleteRequest deleteRequest = new DeleteRequest.Builder().index("cocktails")
            .id(sDto.getCocktailId().toString()).build();
        client.delete(deleteRequest);
        cocktailRepository.deleteById(sDto.getCocktailId());
    }

    @Transactional
    @Cacheable(value = "popular_cocktail", key = "#cocktailId")
    public CocktailResponseDto getCocktail(Long cocktailId) {

        Cocktail cocktail = findById(cocktailId);
        cocktail.incrementCount();

        redisService.increaseViewScore(cocktailId);// 조회점수가 올라간다

        return CocktailResponseDto.from(cocktail);
    }

    @Transactional
    @CachePut(value = "popular_cocktail", key = "#sDto.cocktailId")
    public CocktailResponseDto updateCocktail(UpdateCocktailSDto sDto) {

        Cocktail cocktail = findById(sDto.getCocktailId());

        if (!sDto.getUserId().equals(cocktail.getUser().getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map(req -> {
                Ingredient ingredient = ingredientRepository.getReferenceById(
                    req.getIngredientId());
                return CocktailIngredient.of(cocktail, ingredient, req.getVolume());
            }).toList();

        cocktail.update(sDto.getName(), sDto.getDescription(), sDto.getRecipe(), ingredientList);
        operations.update(CocktailDocument.from(cocktail));

        return CocktailResponseDto.from(cocktail);
    }

    public List<CocktailResponseDto> searchByCondition(findCocktailSDto sDto) throws IOException{

        BoolQuery.Builder builder = QueryBuilders.bool();

        if(!sDto.getRecipeType().isBlank()){

            boolean isOfficial = (sDto.getRecipeType().toUpperCase().equals(RecipeType.OFFICIAL.name()));

            builder.must(Query.of(q -> q.term(m -> m.field("isOfficial")
                    .value(isOfficial))));
        }


        if (!sDto.getKeyword().isBlank()) {
            addShouldIfNotNull(builder, sDto.getKeyword(), "name", 4.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "description", 2.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "recipe", 2.0f);
        }

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(COCKTAIL_INDEX)
                .query(q -> q.bool(builder.build())).build();

        SearchResponse<CocktailDocument> response = client.search(searchRequest, CocktailDocument.class);

        Map<Long, Integer> responseInr = response.hits().hits().stream()
                .filter(hit -> hit.source() != null)
                .collect(Collectors.toMap(

                        hit -> Long.valueOf(hit.source().getId()),
                        hit -> hit.score().intValue()
                ));

        List<CocktailResponseDto> cocktailResponseDtoList = cocktailRepository.findAllById(responseInr.keySet())
                .stream().map(CocktailResponseDto::from)
                .sorted(Comparator.comparing(
                        doc -> responseInr.get(doc.getId()),
                        Comparator.reverseOrder()))
                .toList();

        return cocktailResponseDtoList;
    }


    @Cacheable(value = "popular_cocktail", key = "'popular'")
    public Page<CocktailResponseDto> getPopularCocktails(Pageable pageable) {

        List<CocktailResponseDto> responseDtoList = redisService.getPopularCocktails(
                pageable.getPageSize())
            .stream().map(this::findById)
            .map(CocktailResponseDto::from).toList();

        return new PageImpl<>(responseDtoList, pageable, responseDtoList.size());
    }

    @CachePut(value = "popular_cocktail", key = "'popular'")
    @Scheduled(cron = "0 0 * * * ?") // 1시간마다 캐시데이터 갱신되어 인기칵테일 반영
    public Page<CocktailResponseDto> updatePopularCocktailsCache() {
        log.info("ttl이 실행된다");
        return getPopularCocktails(PageRequest.of(0, 10)); // 기본 Pageable 설정
    }

    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }

    private void addShouldIfNotNull(BoolQuery.Builder builder, String condition, String fieldName, float boost){
        builder.should(Query.of(q -> q.match(m -> m.field(fieldName)
                .query(condition.toLowerCase()).boost(boost))));
    }
}
