package salute.oneshot.domain.cocktail.repository;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsSetQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.cocktail.dto.service.SearchCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.findCocktailSDto;
import salute.oneshot.domain.cocktail.entity.CocktailDocument;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CocktailElasticQueryRepository {

    private final ElasticsearchClient client;
    private final IngredientRepository ingredientRepository;

    private static final String COCKTAIL_INDEX = "cocktails";
    private static final String INGR_FIELD = "ingredients";

    public SearchResponse<CocktailDocument> searchByCondition(findCocktailSDto sDto) throws IOException {

        int page = sDto.getPageable().getPageNumber();
        int size = sDto.getPageable().getPageSize();

        BoolQuery.Builder builder = QueryBuilders.bool();

        if (!sDto.getRecipeType().isBlank()) {

            boolean isOfficial = (sDto.getRecipeType().toUpperCase().equals(RecipeType.OFFICIAL.name()));

            builder.filter(Query.of(q -> q.term(m -> m.field("isOfficial")
                    .value(isOfficial))));
        }


        if (!sDto.getKeyword().isBlank()) {
            addShouldIfNotNull(builder, sDto.getKeyword(), "name", 4.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "description", 2.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "recipe", 2.0f);
        }

        builder.minimumShouldMatch("1");

        SearchResponse<CocktailDocument> response = searchByQuery(builder.build(), page, size);

        return response;
    }

    public SearchResponse<CocktailDocument> findCocktailsByIngr(SearchCocktailSDto sDto) throws IOException {

        List<Ingredient> ingredientList = ingredientRepository.findAllById(sDto.getIngredientIds());

        Set<String> termSet = ingredientList.stream()
                .flatMap(ingr -> ingr.getCategory().equals(IngredientCategory.OTHER)
                        ? Stream.of(ingr.getName())
                        : Stream.of(ingr.getName(), ingr.getCategory().toString()))
                .collect(Collectors.toSet());


        BoolQuery.Builder queryBuilder = QueryBuilders.bool();

        if (sDto.getRecipeType() != null) {
            queryBuilder.must(Query.of(q -> q.term(w -> w.field("isOfficial")
                    .value(sDto.getRecipeType().equals(RecipeType.OFFICIAL)))));
        }


        if (sDto.getIsCraftable()) {
            Script script = new Script.Builder().source("doc['ingredients'].size()").build();
            TermsSetQuery termsSetQuery = QueryBuilders.termsSet().field(INGR_FIELD)
                    .terms(new ArrayList<>(termSet))
                    .minimumShouldMatchScript(script)
                    .build();

            queryBuilder.filter(termsSetQuery._toQuery());
        } else {
            for (String term : termSet) {
                queryBuilder.should(Query.of(q -> q.match(m -> m.field(INGR_FIELD).query(term))));
            }
            queryBuilder.minimumShouldMatch("1");
        }

        SearchResponse<CocktailDocument> response = searchByQuery(queryBuilder.build(), sDto.getPage(), sDto.getSize());

        return response;
    }


    private void addShouldIfNotNull(BoolQuery.Builder builder, String condition, String fieldName, float boost) {
        builder.should(Query.of(q -> q.matchPhrase(m -> m.field(fieldName)
                .query(condition.toLowerCase()).boost(boost))));
    }


    private SearchResponse<CocktailDocument> searchByQuery(BoolQuery query, int page, int size) throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(COCKTAIL_INDEX)
                .from(page * size)
                .size(size)
                .query(q -> q.bool(query)).build();

        return client.search(searchRequest, CocktailDocument.class);
    }
}
