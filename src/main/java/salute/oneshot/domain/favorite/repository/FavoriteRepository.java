package salute.oneshot.domain.favorite.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.favorite.entity.Favorite;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT EXISTS(" +
            "SELECT 1 FROM Favorite f " +
            "WHERE f.cocktail.id = :cocktailId AND f.user.id = :userId)")
    boolean existsByRecipeIdAndUserId(
            @Param("cocktailId") Long cocktailId,
            @Param("userId") Long userId
    );

    Page<Favorite> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = "cocktail")
    Optional<Favorite> findByIdAndUserId(Long favoriteId, Long userId);
}
