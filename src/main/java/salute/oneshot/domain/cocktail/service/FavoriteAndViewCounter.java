package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepositoryImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteAndViewCounter {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;


    public void incrementViewCountAndScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey,"viewCount",1);// cocktail_count::1 이라는 키의 viewcount를 1 증가 시킨다
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY, cocktailScoreKey, 1);// cocktail_score Set에 cocktail_score::1 의 점수가 올라간다


       String temp = redisTemplate.opsForZSet().randomMember(RedisConst.COCKTAIL_SCORE_KEY);
    }

    public void incrementFavoriteScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey, "favoriteCount", 1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY,cocktailScoreKey, 2);
    }


    @Transactional
    @Scheduled(cron = "0 0/2 * * * ?")
    public void updateCocktailViewAndFavoriteCountToDB() {//cocktail_count::*라는 키를 모두 지운다

        Set<String> scoreKeys = redisTemplate.keys(RedisConst.COCKTAIL_COUNT_KEY_PREFIX + "*");
        Iterator<String> it = scoreKeys.iterator();

        while (it.hasNext()) {
            String key = it.next();

            Long cocktailId = Long.parseLong(key.split("::")[1]);

            Integer viewCnt = (Integer) redisTemplate.opsForHash().get(key, "viewCnt");
            cocktailQueryRepository.addViewCntFromRedis(cocktailId, viewCnt);

            Integer favoriteCnt = (Integer) redisTemplate.opsForHash().get(key, "favoriteCnt");
            cocktailQueryRepository.addFavoriteCntFromRedis(cocktailId, favoriteCnt);

            redisTemplate.delete(key);
        }
    }
}

