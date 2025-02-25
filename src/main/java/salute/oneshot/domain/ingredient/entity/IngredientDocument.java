package salute.oneshot.domain.ingredient.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Document(indexName = "ingredients")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Setting(settingPath = "elasticsearch/ingredient/ingredient-settings.json")
@Mapping(mappingPath = "elasticsearch/ingredient/ingredient-mappings.json")
public class IngredientDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;


    public static IngredientDocument from(Ingredient ingredient){

        String id = String.valueOf(ingredient.getId());
        String name = ingredient.getCategory().name().toLowerCase();

        return new IngredientDocument(id,
                ingredient.getName(),
                ingredient.getDescription(),
                name);
    }
}
