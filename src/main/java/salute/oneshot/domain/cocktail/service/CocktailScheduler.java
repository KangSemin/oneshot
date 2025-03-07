package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepositoryImpl;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.util.RedisConst;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CocktailScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailRepository cocktailRepository;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;

    private final int TOP_N = 10;

    @Scheduled(cron = "0 0 * * * ?")
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

    @Transactional
    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateCocktailViewAndFavoriteCountToDB() {
        log.info("데이터 정합성 맞춤");

        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(RedisConst.COCKTAIL_COUNT_KEY_PREFIX + "*") // 특정 패턴의 키만 검색
                .count(100)
                .build();// 키를 100대만 가지고 온다



        Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(
                redisConnection -> redisConnection.scan(scanOptions)
        );

        Set<String> keysToDelete = new HashSet<>();

        while (cursor.hasNext()) {
            String key = new String(cursor.next());

            Long cocktailId = Long.parseLong(key.split("::")[1]);


            String viewCntStr = (String) redisTemplate.opsForHash().get(key, "viewCount");
            Integer viewCnt = (viewCntStr != null) ? Integer.parseInt(viewCntStr) : 0;
            cocktailQueryRepository.addViewCntFromRedis(cocktailId, viewCnt);

            String favCntStr = (String) redisTemplate.opsForHash().get(key, "favoriteCount");
            Integer favoriteCnt = (favCntStr != null) ? Integer.parseInt(favCntStr) : 0;
            cocktailQueryRepository.addFavoriteCntFromRedis(cocktailId, favoriteCnt);

            keysToDelete.add(key);
        }

        if (!keysToDelete.isEmpty()) {
            redisTemplate.delete(keysToDelete);
        }
    }


    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }
}
