package salute.oneshot.domain.recipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.recipe.repository.RecipeRepository;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

}
