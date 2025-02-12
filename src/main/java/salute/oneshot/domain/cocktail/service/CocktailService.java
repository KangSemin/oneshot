package salute.oneshot.domain.cocktail.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.dto.service.CreateCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.DeleteCocktailSDto;
import salute.oneshot.domain.cocktail.dto.service.UpdateCocktailSDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.CocktailIngredient;
import salute.oneshot.domain.cocktail.entity.RecipeType;
import salute.oneshot.domain.cocktail.repository.CocktailIngredientRepository;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepository;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;

    @Transactional
    public void createCocktail(CreateCocktailSDto sDto) {

        User user = userRepository.getReferenceById(sDto.getUserId());

        Cocktail cocktail = Cocktail.of(sDto.getName(), sDto.getDescription(), sDto.getRecipe(),
            RecipeType.CUSTOM, user, null);

        cocktailRepository.save(cocktail);

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map( req-> {
                    Ingredient ingredient = ingredientRepository.getReferenceById(req.getIngredientId());
                    return CocktailIngredient.of(cocktail, ingredient , req.getVolume());
                }).toList();

        cocktailIngredientRepository.saveAll(ingredientList);


    }

    @Transactional
    public void deleteCocktail(DeleteCocktailSDto sDto) {
        cocktailRepository.deleteById(sDto.getCocktailId());
    }

    public CocktailResponseDto getCocktail(Long cocktailId) {

        Cocktail cocktail = findById(cocktailId);

        return CocktailResponseDto.from(cocktail);
    }

    @Transactional
    public CocktailResponseDto updateCocktail(UpdateCocktailSDto sDto) {

        Cocktail cocktail = findById(sDto.getCocktailId());

        List<CocktailIngredient> ingredientList = sDto.getIngredientList().stream()
            .map( req-> {
                Ingredient ingredient = ingredientRepository.getReferenceById(req.getIngredientId());
                return CocktailIngredient.of(cocktail, ingredient , req.getVolume());
            }).toList();

        cocktail.update(sDto.getName(),sDto.getDescription(),sDto.getRecipe(),ingredientList);

        return CocktailResponseDto.from(cocktail);
    }

    private Cocktail findById(Long cocktailId) {
        return cocktailRepository.findById(cocktailId)
            .orElseThrow(()->new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));
    }
}
