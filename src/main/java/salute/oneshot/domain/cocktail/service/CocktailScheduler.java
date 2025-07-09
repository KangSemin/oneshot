package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.global.util.RedisConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CocktailScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailService cocktailService;

    private final int scanCount = 1000;
    private final int TOP_N = 10;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void updateCocktailViewCountToDB() {

        Map<Object, Object> rawMap = redisTemplate.opsForHash().entries(RedisConst.COCKTAIL_VIEW_COUNT_KEY);

        Map<Long, Integer> viewCountMap = rawMap.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Long.parseLong(e.getKey().toString()),
                        e -> Integer.parseInt(e.getValue().toString())));

        cocktailService.updateViewCount(viewCountMap);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void updatePopularCocktails() {
        if(!redisTemplate.hasKey(RedisConst.COCKTAIL_SCORE_KEY)){return;}
        redisTemplate.rename(RedisConst.COCKTAIL_SCORE_KEY, RedisConst.COCKTAIL_SCORE_SNAPSHOT_KEY);
        List<String> popularCocktailIdList = redisTemplate.opsForZSet().reverseRange(RedisConst.COCKTAIL_SCORE_SNAPSHOT_KEY, 0, TOP_N - 1).stream().toList();
        redisTemplate.opsForList().rightPushAll(RedisConst.POPULAR_COCKTAIL_KEY, popularCocktailIdList);
        redisTemplate.delete(RedisConst.COCKTAIL_SCORE_SNAPSHOT_KEY);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void abusingReset(){
        List<String> byteKeyList = new ArrayList<>();
        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(RedisConst.COCKTAIL_VIEW_ABUSING_KEY + "*")
                .count(scanCount)
                .build();

        try (Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(
                connection -> connection.scan(scanOptions))) {
            while (cursor.hasNext()) {
                byteKeyList.add(new String(cursor.next()));
            }
        }
        redisTemplate.delete(byteKeyList);
    }
}