package salute.oneshot.domain.shipping.entity;

import lombok.Getter;

@Getter
public enum ShippingStatus {
    REGISTERED("운송장등록"),
    IN_TRANSIT("배송중"),
    DELIVERED("배송완료"),
    RETURNED("반송");

    private final String description;

    ShippingStatus(String description) {
        this.description = description;
    }
}
