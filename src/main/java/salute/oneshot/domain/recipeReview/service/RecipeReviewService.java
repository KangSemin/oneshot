package salute.oneshot.domain.recipeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.recipeReview.repository.RecipeReviewRepository;

@Service
@RequiredArgsConstructor
public class RecipeReviewService {

    private final RecipeReviewRepository recipeReviewRepository;
}
