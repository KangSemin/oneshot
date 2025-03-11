package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.domain.coupon.dto.request.UserCpnRequestDto;
import salute.oneshot.domain.coupon.entity.Coupon;

import java.time.LocalDateTime;

public class CouponTestFactory {

    public static final Long COUPON_ID = 1L;
    public static final Long USER_COUPON_ID = 1L;
    public static final String COUPON_NAME = "1000원 할인쿠폰";
    public static final int DISCOUNT_VALUE = 1000;
    public static final String START_DATE = "2025-03-01";
    public static final String START_TIME = "00:00";
    public static final String END_DATE = "2025-03-10";
    public static final String END_TIME = "00:00";
    public static final LocalDateTime START_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-01T00:00:00");
    public static final LocalDateTime END_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-10T00:00:00");

    public static Coupon createCoupon() {
        Coupon coupon = Coupon.of(
                COUPON_NAME,
                DISCOUNT_VALUE,
                START_LOCAL_DATE_TIME,
                END_LOCAL_DATE_TIME);
        ReflectionTestUtils.setField(coupon,"id",COUPON_ID);

        return coupon;
    }

    public static Coupon createCoupon2() {
        Coupon coupon = Coupon.of(
                "2000원 할인쿠폰",
                2000,
                LocalDateTime.parse("2025-03-05T00:00:00"),
                LocalDateTime.parse("2025-03-11T00:00:00"));
                ReflectionTestUtils.setField(coupon,"id",2L);

        return coupon;
    }

    public static Coupon createCoupon3() {
        Coupon coupon = Coupon.of(
                "3000원 할인쿠폰",
                3000,
                LocalDateTime.parse("2025-03-02T00:00:00"),
                LocalDateTime.parse("2025-03-09T00:00:00"));
        ReflectionTestUtils.setField(coupon,"id",3L);

        return coupon;
    }

    public static CpnRequestDto createCpnRequestDto() {
        return CpnRequestDto.of(COUPON_NAME, DISCOUNT_VALUE, START_DATE, START_TIME, END_DATE, END_TIME );
    }

    public static UserCpnRequestDto createUserCpnRequestDTo() {
        return UserCpnRequestDto.of(COUPON_ID);
    }
}
