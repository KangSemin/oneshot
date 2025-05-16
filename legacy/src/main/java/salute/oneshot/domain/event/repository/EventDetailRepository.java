package salute.oneshot.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.event.entity.EventDetail;

public interface EventDetailRepository extends JpaRepository<EventDetail, Long> {
}
