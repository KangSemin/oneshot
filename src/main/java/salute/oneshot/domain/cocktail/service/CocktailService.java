package salute.oneshot.domain.cocktail.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.*;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.CocktailDocument;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.repository.CocktailIngredientRepository;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.cocktail.repository.CocktailElasticQueryRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;
import salute.oneshot.global.util.RedisConst;
import salute.oneshot.global.util.S3Util;

@Service
@Slf4j
@RequiredArgsConstructor
public class CocktailService {

    private static final String COCKTAIL_INDEX = "cocktails";
    private static final String INGR_FIELD = "ingredients";

    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;
    private final ElasticsearchOperations operations;
    private final ElasticsearchClient client;
    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailScheduler cocktailScheduler;
    private final CocktailElasticQueryRepository searchFinder;

    private final S3Util s3Util;

    @Transactional
    public void createCocktail(CreateCocktailSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        RecipeType recipeType =
                sDto.getUserRole().equals(UserRole.USER) ? RecipeType.CUSTOM : RecipeType.OFFICIAL;

        String imageUrl = uploadCocktailImage(sDto.getMultipartFile());

        Cocktail cocktail = Cocktail.of(sDto.getName(), sDto.getDescription(), sDto.getRecipe(),
                recipeType, user, new ArrayList<>(), imageUrl);

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

    private String uploadCocktailImage(MultipartFile imageFile) {
        String imageUrl = "";
        if (imageFile != null) {
            try {
                imageUrl = s3Util.upload(imageFile);
            } catch (IOException e) {}
        }
        return imageUrl;
    }

    @Transactional(readOnly = true)
    public Page<CocktailResponseDto> getCocktailsByIngr(SearchCocktailSDto sDto) throws IOException {


        SearchResponse<CocktailDocument> response = searchFinder.findCocktailsByIngr(sDto);

        List<Long> cocktailIds = response.hits().hits().stream()
                .map(hit -> Long.parseLong(hit.source().getId()))
                .toList();

        Pageable pageable = PageRequest.of(sDto.getPage() - 1, sDto.getSize());
        List<CocktailResponseDto> cocktailList = cocktailRepository.findAllById(cocktailIds)
                                                        .stream().map(CocktailResponseDto::from).toList();

        long total = response.hits().total() == null ? 0 : response.hits().total().value();

        return new PageImpl<>(cocktailList, pageable, total);
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
    public CocktailResponseDto getCocktail(Long cocktailId) {

        log.info("서비스에서의 칵테일 아이디:" + cocktailId );

        Cocktail cocktail = findById(cocktailId);
        return CocktailResponseDto.from(cocktail);
    }

    public void increaseViewCountAndScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey,"viewCount",1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY, cocktailScoreKey, 1);
    }

    @Transactional
    @CachePut(value = RedisConst.POPULAR_COCKTAIL_KEY, key = "#sDto.cocktailId")
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

    @Transactional(readOnly = true)
    public Page<CocktailResponseDto> getIngrByCondition(findCocktailSDto sDto) throws IOException {

        SearchResponse<CocktailDocument> response = searchFinder.searchByCondition(sDto);

        Map<Long, Double> responseCocktail = response.hits().hits().stream()
                .filter(hit -> hit.source() != null)
                .collect(Collectors.toMap(

                        hit -> Long.valueOf(hit.source().getId()),
                        hit -> hit.score().doubleValue()
                ));

        List<CocktailResponseDto> cocktailResponseDtoList = cocktailRepository.findAllById(responseCocktail.keySet().stream().toList())
                .stream().map(CocktailResponseDto::from)
                .sorted(Comparator.comparing(
                        doc -> responseCocktail.get(doc.getId()),
                        Comparator.reverseOrder()))
                .toList();

        long total = response.hits().total() == null ? 0 : response.hits().total().value();

        return new PageImpl<>(cocktailResponseDtoList, sDto.getPageable(), total);
    }


    @Cacheable(cacheNames = "popular_cocktail", key = "'popualr'")
    public List<CocktailResponseDto> getPopularCocktails() {

        return cocktailScheduler.updatePopularCocktails();

    }


    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }


}
