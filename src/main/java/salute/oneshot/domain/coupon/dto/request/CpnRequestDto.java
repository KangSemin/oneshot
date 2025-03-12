package salute.oneshot.domain.coupon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.common.entity.ValidationConst;
import salute.oneshot.domain.coupon.entity.CouponValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CpnRequestDto {

    @NotBlank(message = CouponValidationConst.CPN_NAME_MESSAGE)
    private final String couponName;

    @NotNull(message = CouponValidationConst.DISCOUNT_VALUE_MESSAGE)
    @Positive(message = CouponValidationConst.DISCOUNT_VALUE_POSITIVE)
    private final int discountValue;

    @NotBlank(message = ValidationConst.DATE_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.DATE_REG,
            message = ValidationConst.DATE_TYPE_MESSAGE)
    private final String startDate;

    @NotBlank(message = ValidationConst.TIME_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.TIME_REG,
            message = ValidationConst.TIME_TYPE_MESSAGE)
    private final String startTime;

    @NotBlank(message = ValidationConst.DATE_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.DATE_REG,
            message = ValidationConst.DATE_TYPE_MESSAGE)
    private final String endDate;

    @NotBlank(message = ValidationConst.TIME_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.TIME_REG,
            message = ValidationConst.TIME_TYPE_MESSAGE)
    private final String endTime;
}
