package salute.oneshot.domain.cocktail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

}
