package salute.oneshot.elasticSearch;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import salute.oneshot.domain.ingredient.entity.Ingredient;


@Getter
@Document(indexName = "ingredients", createIndex = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientDocument {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private Double avb;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;



    public static IngredientDocument from(Ingredient ingredient){
        return new IngredientDocument(ingredient.getId(),
                                      ingredient.getName(),
                                      ingredient.getAvb(),
                                      ingredient.getDescription(),
                                      ingredient.getCategory().name());
    }
}
