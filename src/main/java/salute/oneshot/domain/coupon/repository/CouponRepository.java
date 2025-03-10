package salute.oneshot.domain.coupon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.coupon.entity.Coupon;

import java.time.LocalDateTime;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Modifying
    @Query("DELETE FROM Coupon c WHERE c.id = :couponId")
    int deleteCouponById(@Param("couponId") Long couponId);

    @Query("SELECT c FROM Coupon c " +
            "WHERE (:startTime IS NULL OR c.startTime >= :startTime) " +
            "AND (:endTime IS NULL OR c.endTime <= :endTime)")
    Page<Coupon> findCoupons(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable);

    @Query("SELECT c FROM Coupon c " +
            "WHERE c.startTime <= :startTime " +
            "AND c.endTime >= :endTime")
    Page<Coupon> findCouponsForEvent(
            @Param("startTime") LocalDateTime eventStarTime,
            @Param("endTime") LocalDateTime eventEndTime,
            Pageable pageable);
}