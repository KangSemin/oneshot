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
@Transactional
@RequiredArgsConstructor

public class FavoriteAndViewCounter {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;



    public void incrementViewCountAndScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey,"viewCount",1);// cocktail_count::1 이라는 키의 viewcount를 1 증가 시킨다
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY, cocktailScoreKey, 1);// cocktail_score Set에 cocktail_score::1 의 점수가 올라간다

    }


    public void incrementFavoriteScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey, "favoriteCount",1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY,cocktailScoreKey, 2);
    }



    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateCocktailViewAndFavoriteCountToDB() {//cocktail_count::*라는 키를 모두 지운다

        log.info("데이터 정합성 반영");

        Set<String> scoreKeys = redisTemplate.keys(RedisConst.COCKTAIL_COUNT_KEY_PREFIX + "*");
        Iterator<String> it = scoreKeys.iterator();

        while (it.hasNext()) {
            String key = it.next();

            log.info(key);

            Long cocktailId = Long.parseLong(key.split("::")[1]);

            String viewCntStr = (String) redisTemplate.opsForHash().get(key, "viewCount");
            Integer viewCnt = (viewCntStr != null) ? Integer.parseInt(viewCntStr) : 0;

            log.info("아이디 : "+ cocktailId);
            log.info("조회수 : "+ viewCnt);


            cocktailQueryRepository.addViewCntFromRedis(cocktailId, viewCnt);

            Integer favoriteCnt = (Integer) redisTemplate.opsForHash().get(key, "favoriteCnt");
            cocktailQueryRepository.addFavoriteCntFromRedis(cocktailId, favoriteCnt);

            redisTemplate.delete(key);
        }
    }
}

