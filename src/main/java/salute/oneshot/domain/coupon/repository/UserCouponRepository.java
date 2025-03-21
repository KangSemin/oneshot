package salute.oneshot.domain.coupon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.coupon.entity.UserCoupon;
import salute.oneshot.domain.coupon.entity.UserCouponStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    @Query("SELECT uc FROM UserCoupon uc " +
            "JOIN FETCH uc.coupon " +
            "WHERE uc.id = :userCouponId " +
            "AND uc.user.id = :userId")
    Optional<UserCoupon> findByIdAndUserId(
            @Param("userCouponId") Long userCouponId,
            @Param("userId") Long userId);

    @Query("SELECT u FROM UserCoupon u " +
            "JOIN FETCH u.coupon " +
            "WHERE u.user.id = :userId " +
            "AND :status IS NULL OR u.status = :status")
    Page<UserCoupon> findUserCoupons(
            @Param("userId") Long userId,
            @Param("status") UserCouponStatus status,
            Pageable pageable);

    @Modifying
    @Query("UPDATE UserCoupon uc " +
            "SET uc.status = 'EXPIRED' " +
            "WHERE uc.coupon.endTime < :now " +
            "AND uc.status = 'ISSUED'")
    int updateExpiredUserCoupons(@Param("now") LocalDateTime now);

    @Query("SELECT u FROM UserCoupon u " +
            "WHERE u.id = :userCouponId " +
            "AND u.user.id = :userId " +
            "AND u.status = :status")
    Optional<UserCoupon> findByIdAndUserIdAndStatus(
            @Param("userCouponId") Long userCouponId,
            @Param("userId") Long userId,
            @Param("status") UserCouponStatus status);
}
