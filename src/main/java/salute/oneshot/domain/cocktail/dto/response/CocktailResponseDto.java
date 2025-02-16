package salute.oneshot.domain.cocktail.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.Cocktail;

import java.time.LocalDateTime;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.user.entity.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailResponseDto {

    private Long id;
    private String name;
    private String description;
    private String recipe;
    private RecipeType type;
    private User user;

    private List<IngrResponseDto> ingredientList;
    private Integer likeCount;
    private Double starRate;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CocktailResponseDto from(Cocktail cocktail) {

        List<IngrResponseDto> reponseList = cocktail.getIngredientList().stream()
            .map(ingr -> IngrResponseDto.from(ingr.getIngredient())).toList();

        return new CocktailResponseDto(cocktail.getId(), cocktail.getName(),
            cocktail.getDescription(), cocktail.getRecipe(), cocktail.getType(), cocktail.getUser(),
            reponseList, cocktail.getLikeCounts(), cocktail.getStarRate(), cocktail.getCreatedAt(),
            cocktail.getModifiedAt());
    }
}
