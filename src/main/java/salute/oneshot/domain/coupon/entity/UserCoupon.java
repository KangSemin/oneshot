package salute.oneshot.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.entity.BaseEntity;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.InvalidException;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private LocalDateTime usedTime;

    @Enumerated(EnumType.STRING)
    private UserCouponStatus status;

    private UserCoupon(
            User user,
            Coupon coupon
    ) {
        this.user = user;
        this.coupon = coupon;
        this.status = UserCouponStatus.ISSUED;
    }

    public static UserCoupon of(
            User user,
            Coupon coupon
    ) {
        return new UserCoupon(
                user,
                coupon);
    }

    public void useUserCoupon() {
        if (LocalDateTime.now().isAfter(this.coupon.getEndTime())) {
            expire();
            throw new InvalidException(ErrorCode.EXPIRED_COUPON);
        }

        this.usedTime = LocalDateTime.now();
        this.status = UserCouponStatus.USED;
    }


    public void expire() {
        this.status = UserCouponStatus.EXPIRED;
    }
}
