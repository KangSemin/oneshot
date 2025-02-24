package salute.oneshot.domain.ingredient.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "ingredients")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class IngredientDocument {

    @Id
    @Field(type = FieldType.Long)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;



    public static IngredientDocument from(Ingredient ingredient){

        String id = String.valueOf(ingredient.getId());

        return new IngredientDocument(id,
                ingredient.getName(),
                ingredient.getDescription(),
                ingredient.getCategory().name());
    }
}
