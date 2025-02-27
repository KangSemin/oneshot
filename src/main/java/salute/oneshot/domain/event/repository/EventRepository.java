package salute.oneshot.domain.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.entity.EventType;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e " +
            "WHERE (:status IS NULL OR e.status = :status) " +
            "AND (:type IS NULL OR e.eventType = :type) " +
            "AND (:startTime IS NULL OR e.startTime >= :startTime) " +
            "AND (:endTime IS NULL OR e.endTime <= :endTime)")
    Page<Event> findEvents(
            @Param("status") EventStatus status,
            @Param("type") EventType type,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable);

    @Modifying
    @Query("DELETE FROM Event e WHERE e.id = :eventId")
    int deleteEventById(@Param("eventId") Long eventId);
}
