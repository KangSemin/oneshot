package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.favorite.entity.Favorite;

import java.time.LocalDateTime;

public class FavoriteTestFactory {

    public static Favorite createFavorite(Cocktail cocktail) {
        Favorite favorite = Favorite.of(
                UserTestFactory.USER_ID,
                cocktail);

        ReflectionTestUtils.setField(favorite, "id", 1L);
        ReflectionTestUtils.setField(favorite, "createdAt", LocalDateTime.parse("2025-03-10T01:01:00"));

        return favorite;
    }
}
