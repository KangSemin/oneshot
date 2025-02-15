package salute.oneshot.domain.pantry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.pantry.entity.Pantry;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Pantry p where p.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Pantry p where p.userId = :userId and p.ingredient.id in :ingredientIds")
    void deleteByUserIdAndIngredientIds(@Param("userId") Long userId,
        @Param("ingredientIds") List<Long> ingredientIds);

    @Query("SELECT p from Pantry p join fetch p.ingredient where p.userId = :userId")
    List<Pantry> findAllByUserId(Long userId);

    @Query(" select exists (select 1 from Pantry p where p.userId = :userId and p.ingredient.id = :ingredientId)")
    boolean existsByUserIdAndIngredientId(Long userId, Long ingredientId);
}

