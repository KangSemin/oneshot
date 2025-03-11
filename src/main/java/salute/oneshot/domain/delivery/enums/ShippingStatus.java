package salute.oneshot.domain.delivery.enums;

import lombok.Getter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.*;

@Getter
public enum ShippingStatus {
    REGISTERED("운송장등록"),
    IN_TRANSIT("배송중"),
    DELIVERED("배송완료"),
    RETURNED("반송");

    private final String description;
    private static final Map<ShippingStatus, Set<ShippingStatus>>
            ALLOWED_TRANS = new HashMap<>();

    public static ShippingStatus of(String description) {
        return Arrays.stream(ShippingStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_COUPON_STATUS));
    }

    static {
        ALLOWED_TRANS.put(REGISTERED, Set.of(IN_TRANSIT));
        ALLOWED_TRANS.put(IN_TRANSIT, Set.of(DELIVERED, RETURNED));
        ALLOWED_TRANS.put(DELIVERED, Set.of(RETURNED));
        ALLOWED_TRANS.put(RETURNED, Collections.emptySet());
    }

    ShippingStatus(String description) {
        this.description = description;
    }

    public boolean canTransitionTo(ShippingStatus newStatus) {
        return ALLOWED_TRANS.get(this).contains(newStatus);
    }
}
