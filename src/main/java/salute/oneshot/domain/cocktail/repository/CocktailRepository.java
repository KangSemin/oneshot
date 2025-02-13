package salute.oneshot.domain.cocktail.repository;


import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.RecipeType;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {


    boolean existsByIdAndUserId(Long cocktailId, Long userId);

    @Query(
            "SELECT DISTINCT c FROM Cocktail c join fetch c.ingredientList l " +
                    "join fetch l.ingredient " +
                    "WHERE (:type IS NULL OR c.type = :type) " +
                    "AND (:keyword IS NULL OR c.name LIKE CONCAT('%', :keyword ,'%') )"
    )
    Page<Cocktail> findCocktails(Pageable pageable, @Param("keyword") String keyword, @Param("type") RecipeType type);

}
