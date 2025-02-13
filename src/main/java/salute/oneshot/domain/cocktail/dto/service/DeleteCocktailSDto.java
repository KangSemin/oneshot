package salute.oneshot.domain.cocktail.dto.service;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteCocktailSDto {
    private final Long userId;
    private final Long cocktailId;


    public static DeleteCocktailSDto of(Long userId, Long cocktailId) {
        return new DeleteCocktailSDto(userId,cocktailId);
    }

}
