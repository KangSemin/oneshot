package salute.oneshot.domain.ingredientReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.ingredientReview.repository.IngredientReviewRepository;

@Service
@RequiredArgsConstructor
public class IngredientReviewService {

    private IngredientReviewRepository ingredientReviewRepository;

}
