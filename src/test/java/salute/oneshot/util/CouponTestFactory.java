package salute.oneshot.util;

import salute.oneshot.domain.coupon.dto.request.CpnRequestDto;
import salute.oneshot.domain.coupon.dto.request.UserCpnRequestDto;
import salute.oneshot.domain.coupon.entity.Coupon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CouponTestFactory {

    public static final Long COUPON_ID = 1L;
    public static final Long USER_COUPON_ID = 1L;
    public static final String COUPON_NAME = "1000원 할인쿠폰";
    public static final int DISCOUNT_VALUE = 1000;
    public static final String START_DATE = "2025-03-01";
    public static final String START_TIME = "00:00";
    public static final String END_DATE = "2025-04-01";
    public static final String END_TIME = "00:00";
    public static final LocalDateTime START_LOCAL_DATE_TIME = LocalDateTime.of(2025, 3, 1, 0, 0);
    public static final LocalDateTime END_LOCAL_DATE_TIME = LocalDateTime.of(2025, 4, 1, 0, 0);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static Coupon createCoupon() {
        return Coupon.of(COUPON_NAME, DISCOUNT_VALUE, START_LOCAL_DATE_TIME, END_LOCAL_DATE_TIME);
    }

    public static CpnRequestDto createCpnRequestDto() {
        return CpnRequestDto.of(COUPON_NAME, DISCOUNT_VALUE, START_DATE, START_TIME, END_DATE, END_TIME );
    }

    public static UserCpnRequestDto createUserCpnRequestDTo() {
        return UserCpnRequestDto.of(COUPON_ID);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
