package salute.oneshot.elasticSearch;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngrDocResponseDto {

    private Long id;
    private String name;
    private Double avb;
    private String description;
    private String category;

    public static IngrDocResponseDto from(IngredientDocument document){
        return new IngrDocResponseDto(document.getId(), document.getName(), document.getAvb(),
                document.getDescription(), document.getCategory());
    }
}
