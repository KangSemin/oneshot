package salute.oneshot.domain.ingredient.service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.SearchIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientDocument;

import salute.oneshot.domain.ingredient.repository.IngredientElasticSearchRepository;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientElasticSearchRepository elasticRepository;
    private final ElasticsearchClient client;

    private final String INGREDIENT_INDEX = "ingredients";


    @Transactional
    public IngrResponseDto createIngredient(CreateIngrSDto request) {

        Ingredient ingredient = ingredientRepository.save(Ingredient.of(
                request.getName(), request.getDescription(), request.getCategory(), request.getAvb()));

        IngredientDocument ingrDoc = IngredientDocument.from(ingredient);
        elasticRepository.save(ingrDoc);

        return IngrResponseDto.from(ingredient);
    }

    @Transactional
    public IngrResponseDto updateIngredient(UpdateIngrSDto request) {

        Ingredient ingredient = findById(request.getId());

        ingredient.update(request.getName(), request.getDescription(), request.getCategory(),
                request.getAvb());

        IngredientDocument ingrDoc = IngredientDocument.from(ingredient);

        elasticRepository.save(ingrDoc);

        return IngrResponseDto.from(ingredient);
    }

    public IngrResponseDto getIngredient(Long ingredientId) {

        Ingredient ingredient = findById(ingredientId);

        return IngrResponseDto.from(ingredient);
    }

    public Page<IngrResponseDto> getAllIngredients(Pageable pageable) {

        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);

        return ingredients.map(IngrResponseDto::from);
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) {
        String id = String.valueOf(ingredientId);

        elasticRepository.deleteById(id);
        ingredientRepository.deleteById(ingredientId);
    }

    public List<IngrResponseDto> searchByCondition(SearchIngrSDto sDto) throws IOException {

        BoolQuery.Builder builder = QueryBuilders.bool();

        if (!sDto.getKeyword().isBlank()) {
            addShouldIfNotNull(builder, sDto.getKeyword(), "name", 3.0f);
            addShouldIfNotNull(builder, sDto.getKeyword(), "description", 2.0f);
        }

        if (sDto.getCategory() != null) {
            addShouldIfNotNull(builder, sDto.getCategory(), "category", 1.0f);
        }

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(INGREDIENT_INDEX)
                .query(q -> q.bool(builder.build())).build();

        SearchResponse<IngredientDocument> response = client.search(searchRequest, IngredientDocument.class);

        Map<Long, Integer> responseInr = response.hits().hits().stream()
                .filter(hit -> hit.source() != null)
                .collect(Collectors.toMap(

                        hit -> Long.valueOf(hit.source().getId()),
                        hit -> hit.score().intValue()
                ));


        List<IngrResponseDto> ingredientList = ingredientRepository.findAllById(responseInr.keySet())
                .stream().map(IngrResponseDto::from)
                .sorted(Comparator.comparing(
                        doc -> responseInr.get(doc.getId()),
                        Comparator.reverseOrder()))
                .toList();

        return ingredientList;
    }

    private void addShouldIfNotNull(BoolQuery.Builder builder, String condition, String fieldName, float boost){
        builder.should(Query.of(q -> q.match(m -> m.field(fieldName)
                .query(condition.toLowerCase()).boost(boost))));
    }

    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));
    }
}
