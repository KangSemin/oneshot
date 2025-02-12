package salute.oneshot.domain.cocktail.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cocktail.entity.Cocktail;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailResponseDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CocktailResponseDto from(Cocktail cocktail){
        return new CocktailResponseDto(cocktail.getId(), cocktail.getName(),
                cocktail.getDescription(), cocktail.getCreatedAt(), cocktail.getModifiedAt());
    }
}
