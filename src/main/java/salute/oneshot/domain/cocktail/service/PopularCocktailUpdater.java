package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.util.RedisConst;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class PopularCocktailUpdater {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailRepository cocktailRepository;

    private final int TOP_N = 10;

    @Scheduled(cron = "0 */3 * * * ?")
    @CachePut(cacheNames = RedisConst.POPULAR_COCKTAIL_KEY, key = "'popualr'")
    public List<CocktailResponseDto> updatePopularCocktails() {
        log.info("인기 칵테일 업데이트");

        List<Long> topNIds = Objects.requireNonNull(redisTemplate.opsForZSet()
                                    .reverseRange(RedisConst.COCKTAIL_SCORE_KEY, 0, TOP_N - 1))
                                    .stream()
                                    .map(key -> Long.parseLong(key.split("::")[1]))
                                    .toList();

        redisTemplate.delete(RedisConst.COCKTAIL_SCORE_KEY);

        List<CocktailResponseDto> responseDtoList = topNIds.stream().map(this::findById)
                .map(CocktailResponseDto::from).toList();

        return responseDtoList;
    }

    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }
}
