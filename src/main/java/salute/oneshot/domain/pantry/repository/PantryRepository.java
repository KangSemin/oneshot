package salute.oneshot.domain.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.pantry.entity.Pantry;

public interface PantryRepository extends JpaRepository<Pantry, Long> {


    @Query(
    """
    select p from Pantry p
    left join fetch p.pantryIngredientList pi
    left join fetch pi.ingredient
    where p.userId = :userId
    """)
    Pantry findByUser_Id(Long userId);
}
