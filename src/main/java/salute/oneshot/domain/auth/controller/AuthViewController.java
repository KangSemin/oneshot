package salute.oneshot.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.global.util.NonceGenerator;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthViewController {


    @GetMapping("/login")
    public String loginPage() {

        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage() {

        return "auth/signup";
    }
}
