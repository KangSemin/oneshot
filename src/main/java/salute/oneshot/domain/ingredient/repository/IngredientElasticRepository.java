package salute.oneshot.domain.ingredient.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import salute.oneshot.domain.ingredient.entity.IngredientDocument;

@Repository
public interface IngredientElasticSearchRepository extends ElasticsearchRepository<IngredientDocument, String> {
}
