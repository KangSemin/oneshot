package salute.oneshot.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.favorite.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
