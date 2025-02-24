package salute.oneshot.domain.ingredient.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.querydsl.core.BooleanBuilder;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.SearchIngrSDto;
import salute.oneshot.domain.ingredient.dto.service.UpdateIngrSDto;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientDocument;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
//    private final IngrQueryDslRepositoryImp queryDslRepository;
    private final ElasticsearchOperations operations;
    private final ElasticsearchClient client;


    @Transactional
    public IngrResponseDto createIngredient(CreateIngrSDto request) throws IOException {

        Ingredient ingredient = ingredientRepository.save(Ingredient.of(
            request.getName(), request.getDescription(), request.getCategory(), request.getAvb()));

        IngredientDocument ingrDoc = IngredientDocument.from(ingredient);
        operations.save(ingrDoc, IndexCoordinates.of("ingredients"));

        return IngrResponseDto.from(ingredient);
    }

    @Transactional
    public IngrResponseDto updateIngredient(UpdateIngrSDto request) throws IOException{

        Ingredient ingredient = findById(request.getId());

        ingredient.update(request.getName(), request.getDescription(), request.getCategory(),
            request.getAvb());

        IngredientDocument ingrDoc = IngredientDocument.from(ingredient);

        operations.update(ingrDoc);

//        String id = String.valueOf(request.getId());

//        UpdateRequest<IngredientDocument, UpdateIngrSDto> updateRequest = new UpdateRequest.Builder<IngredientDocument, UpdateIngrSDto>()
//                .index("ingredients")
//                .id(id)
//                .doc(request)
//                .build();
//
//        UpdateResponse<IngredientDocument> response = client.update(updateRequest, IngredientDocument.class);

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
    public void deleteIngredient(Long ingredientId) throws IOException{
        String id = String.valueOf(ingredientId);

        DeleteRequest deleteRequest = new DeleteRequest.Builder().index("ingredients").id(id).build();
        DeleteResponse deleteResponse = client.delete(deleteRequest);

        ingredientRepository.deleteById(ingredientId);

    }

    public Page<IngrResponseDto> searchByCondition(SearchIngrSDto sDto) throws IOException {

        List<Query> shouldQueries = new ArrayList<>();

        if (sDto.getName() != null) {
            shouldQueries.add(
                    Query.of(q -> q.match(m -> m.field("name")
                                            .query(sDto.getName().toLowerCase())))
            );
        }
        if (sDto.getDescription() != null) {
            shouldQueries.add(
                    Query.of(q -> q.match(m -> m.field("description")
                                            .query(sDto.getDescription().toLowerCase())))
            );
        }
        if (sDto.getCategory() != null) {
            shouldQueries.add(
                    Query.of(q -> q.match(m -> m.field("category")
                                            .query(sDto.getCategory().toLowerCase())))
            );
        }

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("ingredients")
                .query(q -> q.bool(b -> b.should(shouldQueries))).build();


        SearchResponse<IngredientDocument> response = client.search(searchRequest, IngredientDocument.class);

        List<Long> responseInr = response.hits().hits().stream()
                .map(Hit::source).filter(Objects::nonNull)
                .map(IngredientDocument::getId).mapToLong(Long::valueOf).boxed().toList();

       List<IngrResponseDto> ingredientList = ingredientRepository.findAllById(responseInr)
                                                                    .stream().map(IngrResponseDto::from).toList();

       return new PageImpl<>(ingredientList, sDto.getPageable(), ingredientList.size());

//       return queryDslRepository.findIngrByCondition(responseInr, sDto.getPageable());
    }




    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));
    }


}
