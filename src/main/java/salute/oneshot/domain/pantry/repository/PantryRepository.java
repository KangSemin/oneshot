package salute.oneshot.domain.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.pantry.entity.Pantry;

public interface PantryRepository extends JpaRepository<Pantry, Long> {


    @Query(
    """
    select p from Pantry p
    join fetch p.pantryIngredientList pi
    join fetch pi.ingredient
    where p.user.id = :userId
    """)
    Pantry findByUser_Id(Long userId);


}
