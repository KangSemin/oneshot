package salute.oneshot.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.event.entity.FreeEventWinner;

public interface FreeEventWinnerRepository extends JpaRepository<FreeEventWinner, Long> {
}
