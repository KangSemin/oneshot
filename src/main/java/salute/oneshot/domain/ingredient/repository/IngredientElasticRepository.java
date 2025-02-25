package salute.oneshot.domain.ingredient.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import salute.oneshot.domain.ingredient.entity.IngredientDocument;


public interface IngredientElasticRepository extends ElasticsearchRepository<IngredientDocument, String> {
}
