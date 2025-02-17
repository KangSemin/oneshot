package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepositoryImpl;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class CocktailSchedule {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailQueryDslRepositoryImpl cocktailQueryRepository;


    @Scheduled(cron = "0 0/1 * * * ?")
    @CacheEvict(value = "popular_cocktail", allEntries = true)
    public void updateCocktailViewCountToDB() {
        log.info("캐시와 db에 조회수 정합성 맞춤");
        Set<String> redisKeys = redisTemplate.keys("cocktail_viewCount::*");
        Iterator<String> it = redisKeys.iterator();
        log.info("해당 키로 존재하나"+it.hasNext());
        while (it.hasNext()) {
            String data = it.next();
            log.info("카운트 키:"+ data);
            Long cocktailId = Long.parseLong(data.split("::")[1]);//데이터의 아이디를 뽑는다
            log.info("아이디 뽑기"+cocktailId);
            Integer viewCnt = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(data)));
            addViewCntFromRedis(cocktailId, viewCnt);
            redisTemplate.delete(data);
            redisTemplate.delete( "cocktail_viewCount::"+ cocktailId);
        }
    }

    public void addViewCntFromRedis(Long cocktailId, Integer viewCount) {
        cocktailQueryRepository.addViewCntFromRedis(cocktailId, viewCount);
    }
}
