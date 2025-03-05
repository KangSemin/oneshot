package salute.oneshot.domain.cocktail.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCocktailForUploadRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    private final String description;
    @NotBlank
    private final String recipe;
    @NotNull
    private final List<IngredientRequestDto> ingredientList;
    private final MultipartFile imageFile;


}
