package salute.oneshot.domain.cocktail.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import salute.oneshot.domain.cocktail.entity.CocktailDocument;

public interface CocktailElasticRepository extends ElasticsearchRepository<CocktailDocument,String> {

}
