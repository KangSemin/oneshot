package salute.oneshot.domain.ingredient.service;

import java.io.IOException;
import java.util.Optional;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.error.ErrorResponse;
import salute.oneshot.domain.ingredient.dto.request.UpdateIngrRequestDto;
import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
import salute.oneshot.domain.ingredient.dto.service.CreateIngrSDto;
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
    public void deleteIngredient(Long ingredientId) {

        ingredientRepository.deleteById(ingredientId);
    }

    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.INGREDIENT_NOT_FOUND));
    }


}
