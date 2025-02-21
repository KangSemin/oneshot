package salute.oneshot.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisBlacklistRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY_PREFIX = "blacklist:";

    public void save(String token, long expirationTime) {
        String key = KEY_PREFIX + token;
        redisTemplate.opsForValue().set(key, "blacklisted",
                expirationTime + TimeUnit.MINUTES.toMillis(5),
                TimeUnit.MILLISECONDS);
    }

    public boolean existsByToken(String token) {
        String key = KEY_PREFIX + token;
        return redisTemplate.hasKey(key);
    }

    public void delete(String token) {
        String key = KEY_PREFIX + token;
        redisTemplate.delete(key);
    }
}

