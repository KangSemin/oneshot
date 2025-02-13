package salute.oneshot.domain.favorite.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.favorite.entity.Favorite;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoriteResponseDto {

    private Long recipeId;
    private String name;
    private String description;
    private RecipeType type;
    private LocalDateTime favoritedAt;

    public static FavoriteResponseDto from(Cocktail cocktail, Favorite favorite) {
        return new FavoriteResponseDto(
                cocktail.getId(),
                cocktail.getName(),
                cocktail. getDescription(),
                cocktail.getType(),
                favorite.getCreatedAt());
    }
}
