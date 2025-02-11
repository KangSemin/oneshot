package salute.oneshot.domain.recipeReview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.recipeReview.service.RecipeReviewService;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class RecipeReviewController {

    private final RecipeReviewService recipeReviewService;

}
