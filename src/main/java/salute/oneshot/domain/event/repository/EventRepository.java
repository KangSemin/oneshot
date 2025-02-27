package salute.oneshot.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
