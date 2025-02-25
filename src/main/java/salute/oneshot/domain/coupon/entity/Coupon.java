package salute.oneshot.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.coupon.dto.service.CreateCpnSDto;

import java.time.LocalDateTime;

@Slf4j
@Entity
@Table(name = "coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String couponName;

    @Column(nullable = false)
    private int discountValue;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private Coupon(
            String couponName,
            int discountValue,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.couponName = couponName;
        this.discountValue = discountValue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Coupon from(CreateCpnSDto serviceDtd) {
        return new Coupon(
                serviceDtd.getCouponName(),
                serviceDtd.getDiscountValue(),
                serviceDtd.getStartTime(),
                serviceDtd.getEndTime());
    }
}
