package salute.oneshot.domain.shipping.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public enum ShippingStatus {
    REGISTERED("운송장등록"),
    IN_TRANSIT("배송중"),
    DELIVERED("배송완료"),
    RETURNED("반송");

    private final String description;
    private static final Map<ShippingStatus, Set<ShippingStatus>>
            ALLOWED_TRANS = new HashMap<>();

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
