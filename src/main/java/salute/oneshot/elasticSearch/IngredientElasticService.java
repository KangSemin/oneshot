package salute.oneshot.elasticSearch;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.cocktail.dto.request.IngredientRequestDto;
import salute.oneshot.domain.ingredient.dto.request.CreateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.ingredientReview.dto.response.IngredientResponseDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientElasticService {

    private final IngredientRepository ingredientRepository;
    private final ElasticsearchOperations elasticsearchOperations;


    @PostConstruct
    public void checkElasticsearchConnection() {
        try {
            boolean exists = elasticsearchOperations.indexOps(Ingredient.class).exists();
            log.info("연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    public IngrDocResponseDto createIngr(CreateIngrRequestDto dto){

        IngredientCategory category = IngredientCategory.valueOf(dto.getCategory());

        Ingredient ingredient = Ingredient.of(dto.getName(), dto.getDescription(), category, dto.getAvb());

        Ingredient savedIngr = ingredientRepository.save(ingredient);

        IngredientDocument ingDoc = IngredientDocument.from(ingredient);
        IngredientDocument saveIngrDocument = elasticsearchOperations.save(ingDoc);

        return IngrDocResponseDto.from(saveIngrDocument);
    }
}
