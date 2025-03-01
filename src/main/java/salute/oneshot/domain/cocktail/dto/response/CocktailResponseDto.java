package salute.oneshot.domain.cocktail.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.Cocktail;

import java.time.LocalDateTime;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.entity.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailResponseDto implements Serializable{

    private Long id;
    private String name;
    private String description;
    private String recipe;
    private RecipeType type;
    private UserResponseDto user;

    private List<IngrResponseDto> ingredientList;
    private Integer likeCount;
    private Double starRate;
    private Integer viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CocktailResponseDto from(Cocktail cocktail) {

        List<IngrResponseDto> ingrReponseList = cocktail.getIngredientList().stream()
            .map(ingr -> IngrResponseDto.from(ingr.getIngredient())).toList();

        UserResponseDto userResponse = UserResponseDto.from(cocktail.getUser());

        return new CocktailResponseDto(cocktail.getId(), cocktail.getName(),
            cocktail.getDescription(), cocktail.getRecipe(), cocktail.getType(), userResponse,

            ingrReponseList, cocktail.getFavoriteCount(), cocktail.getStarRate(), cocktail.getViewCount(), cocktail.getCreatedAt(),
            cocktail.getModifiedAt());
    }
}
