package salute.oneshot.domain.banner.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.banner.entity.Banner;

import java.time.LocalDateTime;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    @Modifying
    @Query("DELETE FROM Banner b WHERE b.id = :id")
    int deleteBannerById(@Param("id") Long id);

    @Query("SELECT b FROM Banner b " +
            "WHERE (:startTime IS NULL OR b.startTime >= :startTime) " +
            "AND (:endTime is NULL OR b.endTime <= :endTime)")
    Page<Banner> findBanners(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable);

    // 추후 배너뷰에서 사용할 예정
    @Query("SELECT b FROM Banner b JOIN FETCH b.event WHERE b.id = :bannerId")
    Banner findEventByBannerId(@Param("bannerId") Long bannerId);
}
