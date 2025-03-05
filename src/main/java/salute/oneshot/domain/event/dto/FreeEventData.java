package salute.oneshot.domain.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FreeEventData {
    private final Long couponId;
    private final String couponName;

    public static FreeEventData of(Long couponId, String couponName) {
        return new FreeEventData(couponId, couponName);
    }
}
