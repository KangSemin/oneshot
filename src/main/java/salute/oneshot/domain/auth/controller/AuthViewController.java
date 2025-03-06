package salute.oneshot.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.global.config.NonceGenerator;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());

        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());

        return "auth/signup";
    }
}
