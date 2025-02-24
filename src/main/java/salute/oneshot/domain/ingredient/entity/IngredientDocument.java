package salute.oneshot.domain.ingredient.entity;

import lombok.Getter;
import lombok.Setter;

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
        return new IngredientDocument(ingredient.getId(),
                ingredient.getName(),
                // ingredient.getAvb(),
                ingredient.getDescription(),
                ingredient.getCategory().name());
    }
}
