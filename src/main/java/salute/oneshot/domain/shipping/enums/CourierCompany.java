package salute.oneshot.domain.shipping.enums;

import lombok.Getter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

@Getter
public enum CourierCompany {
    CJ("대한통운", "04"),
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

    public static CourierCompany of(String code) {
        return Arrays.stream(CourierCompany.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_SHIPPING_CODE));
    }
}
