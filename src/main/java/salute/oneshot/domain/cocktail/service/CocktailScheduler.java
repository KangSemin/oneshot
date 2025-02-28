package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepositoryImpl;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.util.RedisConst;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CocktailScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailRepository cocktailRepository;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;

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

    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateCocktailViewAndFavoriteCountToDB() {
        log.info("데이터 정합성 맞춤");

        Set<String> scoreKeys = redisTemplate.keys(RedisConst.COCKTAIL_COUNT_KEY_PREFIX + "*");
        Iterator<String> it = scoreKeys.iterator();

        while (it.hasNext()) {
            String key = it.next();

            Long cocktailId = Long.parseLong(key.split("::")[1]);

            String viewCntStr = (String) redisTemplate.opsForHash().get(key, "viewCount");
            Integer viewCnt = (viewCntStr != null) ? Integer.parseInt(viewCntStr) : 0;

            cocktailQueryRepository.addViewCntFromRedis(cocktailId, viewCnt);

            String favCntStr = (String) redisTemplate.opsForHash().get(key, "favoriteCount");
            Integer favoriteCnt = (favCntStr != null) ? Integer.parseInt(favCntStr) : 0;

            cocktailQueryRepository.addFavoriteCntFromRedis(cocktailId, favoriteCnt);

            redisTemplate.delete(key);
        }
    }

    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }
}
