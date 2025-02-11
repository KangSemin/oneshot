package salute.oneshot.domain.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.ingredient.repository.IngredientRepository;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
}
