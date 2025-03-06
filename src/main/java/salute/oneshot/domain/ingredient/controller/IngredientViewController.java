package salute.oneshot.domain.ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import salute.oneshot.global.config.NonceGenerator;

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

    
}
