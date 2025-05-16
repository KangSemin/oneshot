package salute.oneshot.domain.banner.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.banner.entity.Banner;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Query("SELECT b FROM Banner b JOIN FETCH b.event " +
            "WHERE b.event.id = :eventId")
    Optional<Banner> findByEventId(Long eventId);

    @Modifying
    @Query("UPDATE Banner b SET b.isActive = true WHERE b.startTime BETWEEN :now AND :endOfDay")
    void activateBanner(@Param("now") LocalDateTime now,
                        @Param("endOfDay") LocalDateTime endOfDay);

    @Modifying
    @Query("UPDATE Banner b SET b.isActive = false WHERE b.endTime <= :now")
    void deactivateBanner(LocalDateTime now);
}