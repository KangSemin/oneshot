package salute.oneshot.domain.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FreeEventData {

    private final List<Coupon> coupons;
    private final int limitCount;
    private final List<User> winners;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Coupon {
        private final Long couponId;
        private final String couponName;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        private final Long userId;
        private final boolean couponIssued;
    }
}
