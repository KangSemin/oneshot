package salute.oneshot.domain.cocktail.repository;


import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.RecipeType;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> , CocktailQueryDslRepository{

    @Query("""
        SELECT DISTINCT c FROM Cocktail c
        LEFT JOIN FETCH c.user
        LEFT JOIN FETCH c.ingredientList ci
        LEFT JOIN FETCH ci.ingredient
        WHERE c.id = :id
        """)
    Optional<Cocktail> findById(@Param("id") Long id);

    boolean existsByIdAndUserId(Long cocktailId, Long userId);

    @Query("""
        SELECT DISTINCT c FROM Cocktail c join fetch c.ingredientList l
        join fetch l.ingredient
        join fetch c.user
        WHERE (:type IS NULL OR c.type = :type)
        AND (:keyword IS NULL OR c.name LIKE CONCAT('%', :keyword ,'%') )
        """)
    Page<Cocktail> findCocktails(Pageable pageable, @Param("keyword") String keyword, @Param("type") RecipeType type);

    @Query("""
        SELECT c FROM Cocktail c
        LEFT JOIN FETCH c.user
        LEFT JOIN FETCH c.ingredientList ci
        LEFT JOIN FETCH ci.ingredient
        WHERE c.id in :ids
        """)
    Page<Cocktail> findAllById(List<Long> ids, Pageable pageable);

}
