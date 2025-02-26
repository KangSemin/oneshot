package salute.oneshot.domain.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.coupon.entity.UserCoupon;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    @Modifying
    @Query("UPDATE UserCoupon uc " +
            "SET uc.status = 'EXPIRED' " +
            "WHERE uc.coupon.endTime < :now " +
            "AND uc.status = 'ISSUED'")
    int updateExpiredUserCoupons(@Param("now") LocalDateTime now);

    @Query("SELECT uc FROM UserCoupon uc " +
            "JOIN FETCH uc.coupon " +
            "WHERE uc.id = :userCouponId " +
            "AND uc.user.id = :userId")
    Optional<UserCoupon> findByIdAndUserId(
            @Param("userCouponId") Long userCouponId,
            @Param("userId") Long userId);
}
