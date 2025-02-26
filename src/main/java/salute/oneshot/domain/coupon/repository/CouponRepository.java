package salute.oneshot.domain.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.coupon.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
