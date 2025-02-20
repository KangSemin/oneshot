package salute.oneshot.domain.cocktail.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.CreateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.DeleteCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.UpdateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.findCocktailSDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.repository.CocktailIngredientRepository;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.user.entity.User;
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
    private final RedisService redisService;

    @Transactional
    public void createCocktail(CreateCocktailSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        Cocktail cocktail = Cocktail.of(sDto.getName(), sDto.getDescription(), sDto.getRecipe(),
            RecipeType.CUSTOM, user, null);

        cocktailRepository.save(cocktail);

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map(req -> {
                Ingredient ingredient = ingredientRepository.getReferenceById(
                    req.getIngredientId());
                return CocktailIngredient.of(cocktail, ingredient, req.getVolume());
            }).toList();

        cocktailIngredientRepository.saveAll(ingredientList);


    }

    @Transactional(readOnly = true)
    public Page<CocktailResponseDto> findCocktailsByIngr(SearchCocktailSDto sDto) {

        Pageable pageable = PageRequest.of(sDto.getPage()-1,sDto.getSize());

        List<Ingredient> ingredientList = ingredientRepository.findAllById(sDto.getIngredientIds());

        Page<Cocktail> cocktailPage = cocktailRepository.searchCocktailsByIngredients(ingredientList,pageable);

        return cocktailPage.map(CocktailResponseDto::from);

    }

    @Transactional
    public void deleteCocktail(DeleteCocktailSDto sDto) {

        if (!cocktailRepository.existsByIdAndUserId(sDto.getCocktailId(), sDto.getUserId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }
        cocktailRepository.deleteById(sDto.getCocktailId());
    }

    @Transactional
    @Cacheable(value = "popular_cocktail", key ="#cocktailId")
    public CocktailResponseDto getCocktail(Long cocktailId) {

        Cocktail cocktail = findById(cocktailId);
        cocktail.incrementCount();

        redisService.increaseViewScore(cocktailId);// 조회점수가 올라간다

        return CocktailResponseDto.from(cocktail);
    }

    @Transactional
    @CachePut(value = "popular_cocktail", key ="#cocktailId")
    public CocktailResponseDto updateCocktail(UpdateCocktailSDto sDto) {

        Cocktail cocktail = findById(sDto.getCocktailId());

        if (!sDto.getUserId().equals(cocktail.getUser().getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map( req-> {
                Ingredient ingredient = ingredientRepository.getReferenceById(req.getIngredientId());
                return CocktailIngredient.of(cocktail, ingredient , req.getVolume());
            }).toList();

        cocktail.update(sDto.getName(),sDto.getDescription(),sDto.getRecipe(),ingredientList);

        return CocktailResponseDto.from(cocktail);
    }

    public Page<CocktailResponseDto> getCocktails(findCocktailSDto sDto){

        RecipeType type = (sDto.getRecipeType() != null) ? RecipeType.valueOf(sDto.getRecipeType()) : null;

        Page<Cocktail> cocktailPage = cocktailRepository.findCocktails(sDto.getPageable(), sDto.getKeyword(), type);
        return cocktailPage.map(CocktailResponseDto::from);

    }


    @Cacheable(value = "popular_cocktail", key ="'popular'")
    public Page<CocktailResponseDto> getPopularCocktails(Pageable pageable) {

        List<CocktailResponseDto> responseDtoList = redisService.getPopularCocktails(pageable.getPageSize())
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
}
