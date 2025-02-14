package salute.oneshot.domain.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.pantry.entity.PantryIngredient;

public interface PantryIngredientRepository extends JpaRepository<PantryIngredient,Long> {

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("delete from PantryIngredient p where p.pantry.id = :pantryId")
    void deleteByPantryId(@Param("pantryId") Long pantryId);
}
