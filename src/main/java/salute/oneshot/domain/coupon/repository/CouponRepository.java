package salute.oneshot.domain.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.coupon.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Modifying
    @Query("DELETE FROM Coupon c WHERE c.id = :couponId")
    int deleteCouponById(@Param("couponId") Long couponId);
}
