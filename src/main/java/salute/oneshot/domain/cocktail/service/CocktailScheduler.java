package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.connection.RedisSetCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.global.util.RedisConst;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CocktailScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailService cocktailService;
    private final CocktailRepository cocktailRepository;

    private final int scanCount = 1000;
    private final int TOP_N = 10;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateCocktailViewCountToDB() {
    }

    @Scheduled(cron = "0 0 * * * ?")// 인기칵테일 갱신 메서드
    @CachePut(cacheNames = "cocktail", key = "'popualr'")
    public List<CocktailResponseDto> updatePopularCocktails() {

        List<Long> popularCocktailIdList = redisTemplate.opsForZSet()
                .reverseRange(RedisConst.COCKTAIL_SCORE_KEY, 0, TOP_N - 1).stream()
                .map(key -> Long.parseLong(key.split("::")[1])).toList();

        redisTemplate.delete(RedisConst.POPULAR_COCKTAIL_KEY);
        List<CocktailResponseDto>  popularCocktailList = cocktailRepository.findAllById(popularCocktailIdList)
                .stream().map(CocktailResponseDto::from).toList();

        return popularCocktailList;
    }

    @Scheduled
    public void abusingReset(){
        List<String> byteKeyList = new ArrayList<>();
        ScanOptions scanOptions = ScanOptions.scanOptions()
                .match(RedisConst.COCKTAIL_VIEW_COUNT_KEY_PREFIX + "*")
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