package salute.oneshot.domain.ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.global.util.NonceGenerator;

@Controller
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping
    public String createIngredient(Model model){

        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        return "ingredient/create-ingredient";
    }

    @GetMapping("/{ingredientId}")
    public String getIngredient(Model model, @PathVariable(name = "ingredientId")Long ingredientId){
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        model.addAttribute("ingredientId", ingredientId);

        return "get-ingredient";
    }
}
