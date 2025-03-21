package salute.oneshot.domain.cocktail.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;
import salute.oneshot.domain.ingredient.entity.Ingredient;

@Getter
@NoArgsConstructor
@Document(indexName = "cocktails")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setting(settingPath = "elasticsearch/cocktail/cocktail-settings.json")
@Mapping(mappingPath = "elasticsearch/cocktail/cocktail-mappings.json")
public class CocktailDocument {

    @Id
    private String id;

    private String name;
    private String description;

    private List<String> ingredients;

    private Boolean isOfficial;

    public static CocktailDocument from(Cocktail cocktail) {
        return new CocktailDocument(cocktail.getId().toString(),
                                    cocktail.getName(),
                                    cocktail.getDescription(),
                                    cocktail.getIngredientList().stream().map(ingr -> ingr.getIngredient().getName()).toList(),
                                    cocktail.getType().equals(RecipeType.OFFICIAL)
        );
    }

    public static CocktailDocument of(Cocktail cocktail, Map<Long, Ingredient> ingredientMap) {
        return new CocktailDocument(cocktail.getId().toString(),
            cocktail.getName(),
            cocktail.getDescription(),
            ingredientMap.values().stream().map(Ingredient::getName).toList(),
            cocktail.getType().equals(RecipeType.OFFICIAL)
        );
    }

}
