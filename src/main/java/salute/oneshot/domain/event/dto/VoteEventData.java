package salute.oneshot.domain.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteEventData {

    private final List<Coupon> coupons;
    private final int limitCount;
    private final List<Candidate> candidates;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Coupon {
        private final Long couponId;
        private final String couponName;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Candidate {
        private final Long cocktailId;
        private final Long userId;
        private final String name;
        private final String recipeLink;
        private final int voteCount;

        private Candidate(Long cocktailId, Long userId, String name) {
            this.cocktailId = cocktailId;
            this.userId = userId;
            this.name = name;
            this.recipeLink = "/api/cocktails/" + cocktailId;
            this.voteCount = 0;
        }
    }
}
