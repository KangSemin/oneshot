package salute.oneshot.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepositoryImpl;

import java.util.Iterator;
import java.util.Set;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor

public class FavoriteAndViewCounter {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;


    public void incrementViewCountAndScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey,"viewCount",1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY, cocktailScoreKey, 1);

    }

    public void incrementFavoriteScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey, "favoriteCount",1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY,cocktailScoreKey, 2);
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
}

