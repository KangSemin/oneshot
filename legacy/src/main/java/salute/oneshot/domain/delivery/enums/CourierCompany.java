package salute.oneshot.domain.delivery.enums;

import lombok.Getter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

@Getter
public enum CourierCompany {
    CJGLS("대한통운", "04"),
    LOTTE("롯데택배", "08"),
    HANJIN("한진택배", "05"),
    LOGEN("로젠택배", "06"),
    POST_OFFICE("우체국택배", "01");

    private final String companyName;
    private final String code;

    CourierCompany(String companyName, String code) {
        this.companyName = companyName;
        this.code = code;
    }

    public static CourierCompany ofName(String companyName) {
        return Arrays.stream(CourierCompany.values())
                .filter(r -> r.name().equalsIgnoreCase(companyName))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_COUPON_STATUS));
    }

    public static CourierCompany ofCode(String code) {
        return Arrays.stream(CourierCompany.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_SHIPPING_CODE));
    }
}
