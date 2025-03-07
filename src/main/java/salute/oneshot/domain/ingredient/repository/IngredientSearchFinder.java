package salute.oneshot.domain.ingredient.repository;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.ingredient.dto.service.SearchIngrSDto;
import salute.oneshot.domain.ingredient.entity.IngredientDocument;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class IngredientSearchFinder {

    private final ElasticsearchClient client;

    private final String INGREDIENT_INDEX = "ingredients";

    public SearchResponse<IngredientDocument> ingrSearchByCondition(SearchIngrSDto sDto) throws IOException {

        int size = sDto.getPageable().getPageSize();
        int page = sDto.getPageable().getPageNumber();
        int from = size * page;

        BoolQuery.Builder builder = QueryBuilders.bool();

        if (sDto.getCategory() != null) {
            builder.filter(Query.of(q -> q.term(m -> m.field("category").value(sDto.getCategory()))));
        }

        if (!sDto.getKeyword().isBlank()) {
            addShouldIfNotNull(builder, sDto.getKeyword(), "name", 3.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "description", 2.0f);
        }

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(INGREDIENT_INDEX)
                .from(from)
                .size(size)
                .query(q -> q.bool(builder.build())).build();

        SearchResponse<IngredientDocument> response = client.search(searchRequest, IngredientDocument.class);

        return response;
    }

    private void addShouldIfNotNull(BoolQuery.Builder builder, String condition, String fieldName, float boost) {
        builder.should(Query.of(q -> q.match(m -> m.field(fieldName)
                .query(condition.toLowerCase()).boost(boost))));
    }
}
