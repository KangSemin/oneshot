package salute.oneshot.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.exception.InvalidException;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepository {

    private final StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "refresh_token:";

    public void save(Long userId, UserRole userRole, String refreshToken) {
        String key = KEY_PREFIX + userId;

        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(key, "token", refreshToken);
        hashOps.put(key, "role", userRole.name());
        redisTemplate.expire(key, Duration.ofDays(7));
    }

    public UserRole findRoleById(Long userId) {
        String key = KEY_PREFIX + userId;
        String userRole =
                (String) redisTemplate.opsForHash().get(key, "role");

        if (userRole == null) {
            throw new InvalidException(ErrorCode.USER_NOT_FOUND);
        }
        return UserRole.of(userRole);
    }

    public boolean validate(Long userId, String refreshToken) {
        String key = KEY_PREFIX + userId;
        String storedToken =
                (String) redisTemplate.opsForHash().get(key, "token");

        return refreshToken.equals(storedToken);
    }

    public void delete(Long userId) {
        String key = KEY_PREFIX + userId;
        redisTemplate.delete(key);
    }
}
