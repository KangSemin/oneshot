package salute.oneshot.domain.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.pantry.entity.Pantry;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    Pantry findByUser_Id(Long userId);
}
