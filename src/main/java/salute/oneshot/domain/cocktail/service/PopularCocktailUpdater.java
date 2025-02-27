package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class PopularCocktailUpdater {

    private final RedisTemplate<String, String> redisTemplate;
    private final CocktailRepository cocktailRepository;

    private final Integer TOP_N = 10;

    /*점수가 가장 높은 칵테일의 아이디를 가져와 칵테일 객체를 얻는다
     */

    @Scheduled(cron = "0 * * * * ?")
    @Cacheable(value = RedisConst.POPULAR_COCKTAIL_KEY, key = "'popular'")
    @CacheEvict(value = RedisConst.COCKTAIL_SCORE_KEY, allEntries = true)
    public List<CocktailResponseDto> updatePopularCocktails() {
        log.info("인기 칵테일 업데이트");


       List<Long> topNIds = Objects.requireNonNull(redisTemplate.opsForZSet()
                       .reverseRange(RedisConst.COCKTAIL_SCORE_KEY, 0, TOP_N - 1))
                           .stream().map(Long::parseLong).toList();

      return cocktailRepository.findAllById(topNIds, PageRequest.of(0, TOP_N - 1))
                                .stream().map(CocktailResponseDto::from).toList();
    }

}
