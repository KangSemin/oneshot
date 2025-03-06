package salute.oneshot.domain.ingredient.dto.service;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

@Getter
public class CreateIngrSDto {

    private final String name;
    private final String description;
    private final IngredientCategory category;

    private final Double avb;

    private final MultipartFile imageFile;



    private CreateIngrSDto(String name, String description, IngredientCategory category,
        Double avb, MultipartFile imageFile) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.avb = avb;
        this.imageFile = imageFile;
    }

    public static CreateIngrSDto of(String name, String description, IngredientCategory category,
        Double avb, MultipartFile imageFile) {
        return new CreateIngrSDto(name, description, category, avb, imageFile);
    }
}
