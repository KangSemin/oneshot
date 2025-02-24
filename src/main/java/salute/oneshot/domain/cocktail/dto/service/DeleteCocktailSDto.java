package salute.oneshot.domain.cocktail.dto.service;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteCocktailSDto {
    private final Long userId;
    private final UserRole userRole;
    private final Long cocktailId;


    public static DeleteCocktailSDto of(Long userId, UserRole userRole, Long cocktailId) {
        return new DeleteCocktailSDto(userId, userRole,cocktailId);
    }

}
