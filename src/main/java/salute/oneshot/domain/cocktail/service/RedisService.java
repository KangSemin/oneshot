package salute.oneshot.domain.cocktail.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static salute.oneshot.domain.cocktail.entity.QCocktail.cocktail;


@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    private static String POPULAR_COCKTAIL_KEY = "popular_cocktail";
    private static String COCKTAIL_VIEWCOUNT_KEY_PREFIX = "cocktail_viewCount::";


    public void increaseViewScore(Long cocktailId) {
        String viewCountKey = COCKTAIL_VIEWCOUNT_KEY_PREFIX + cocktailId;

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

            valueOperations.increment(viewCountKey);
        redisTemplate.opsForZSet().incrementScore(POPULAR_COCKTAIL_KEY, String.valueOf(cocktailId), 1);// 해당 아이디를 키로가지고 있는 score를 증가 시킨다
    }


    public List<Long> getPopularCocktails(int limit) {
        return Objects.requireNonNull(redisTemplate.opsForZSet()
                .reverseRange(POPULAR_COCKTAIL_KEY, 0, limit - 1)).stream().map(Long::parseLong).toList();
    }

}

