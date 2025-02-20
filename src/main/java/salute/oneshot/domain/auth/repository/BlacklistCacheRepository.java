package salute.oneshot.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BlacklistCacheRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String token) {
        String key = "blacklist:" + token;
        redisTemplate.opsForValue().set(key, "blacklisted", 75, TimeUnit.MINUTES);
    }

    public boolean exists(String token) {
        String key = "blacklist:" + token;
        return redisTemplate.hasKey(key);
    }

    public void delete(String token) {
        String key = "blacklist:" + token;
        redisTemplate.delete(key);
    }
}

