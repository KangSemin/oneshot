package salute.oneshot.domain.cocktail.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.user.entity.UserRole;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCocktailSDto {

    private final Long userId;
    private final UserRole userRole;
    private final String name;
    private final String recipe;
    private final String description;
    private final List<IngredientRequestDto> ingredientList;
    private final MultipartFile multipartFile;



    public static CreateCocktailSDto of(Long userId, UserRole userRole, String name, String recipe, String description,
        List<IngredientRequestDto> ingredientList, MultipartFile imageFile) {
        return new CreateCocktailSDto(userId, userRole, name, recipe, description, ingredientList, imageFile);
    }

}
