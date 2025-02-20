package salute.oneshot.domain.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.exception.NotFoundException;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserCachesRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;

    public UserRole cacheUserRole(Long userId) {
        UserRole role = userRepository.findUserRoleById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        String key = "users:" + userId + ":role";
        redisTemplate.opsForValue().set(key, role, 3600, TimeUnit.SECONDS);
        return role;
    }

    public UserRole getCacheUserRole(Long userId) {
        String key = "users:" + userId + ":role";
        Object roleObj = redisTemplate.opsForValue().get(key);
        return roleObj != null ? UserRole.of(roleObj.toString()) : null;
    }
}
