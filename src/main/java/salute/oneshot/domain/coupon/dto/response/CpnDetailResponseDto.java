package salute.oneshot.domain.coupon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.coupon.entity.Coupon;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CpnDetailResponseDto {

    private Long id;
    private String couponName;
    private int discountValue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public static CpnDetailResponseDto from(Coupon coupon) {
        return new CpnDetailResponseDto(
                coupon.getId(),
                coupon.getCouponName(),
                coupon.getDiscountValue(),
                coupon.getStartTime(),
                coupon.getEndTime());
    }
}
