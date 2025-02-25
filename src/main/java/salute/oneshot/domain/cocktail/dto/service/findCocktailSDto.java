package salute.oneshot.domain.cocktail.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class findCocktailSDto {
    private Pageable pageable;
    private String keyword;
    private Boolean recipeType;



    public static findCocktailSDto of(Pageable pageable, String keyword, Boolean recipeType){
        return new findCocktailSDto(pageable, keyword, recipeType);
    }


}
