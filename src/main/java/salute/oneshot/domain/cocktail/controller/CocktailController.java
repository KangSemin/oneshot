package salute.oneshot.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.cocktail.service.CocktailService;

@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;
}
