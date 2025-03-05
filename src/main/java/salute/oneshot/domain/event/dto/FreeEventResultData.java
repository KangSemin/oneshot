package salute.oneshot.domain.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FreeEventResultData {
    private final Long couponId;
    private final List<Winner> winners;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Winner {
        private final Long userId;
        private final Long couponId;
        private final LocalDateTime winTime;
    }

    public static FreeEventResultData from(
            FreeEventData freeEventData,
            List<Winner> winners) {
        return new FreeEventResultData(
                freeEventData.getCouponId(),
                winners);
    }
}