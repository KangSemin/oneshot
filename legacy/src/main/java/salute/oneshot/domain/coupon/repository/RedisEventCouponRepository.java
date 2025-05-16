package salute.oneshot.domain.coupon.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisEventCouponRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "event_coupons";
    private static final int TTL = 5;

    public void saveCouponIds(List<String> couponIds) {
        redisTemplate.opsForValue().set(KEY, couponIds, TTL, TimeUnit.MINUTES);
    }

    public boolean isValidCouponId(String couponId) {
        List<String> validIds =
                (List<String>) redisTemplate.opsForValue().get(KEY);

        if (validIds == null) {
            return false;
        }
        return validIds.contains(couponId);
    }
}