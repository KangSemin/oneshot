package salute.oneshot.domain.cocktail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.global.config.NonceGenerator;

@Controller
@RequestMapping("/cocktails")
@RequiredArgsConstructor
public class CocktailViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping
    public String testUploadPage(Model model) {
        // 시큐리티 CSP 설정용
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        return "cocktail/cocktail-create";
    }
}
