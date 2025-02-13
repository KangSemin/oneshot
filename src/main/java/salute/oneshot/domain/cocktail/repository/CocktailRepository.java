package salute.oneshot.domain.cocktail.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.cocktail.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {


    boolean existsByIdAndUserId(Long cocktailId, Long userId);

}
