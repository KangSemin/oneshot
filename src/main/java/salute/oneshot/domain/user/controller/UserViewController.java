package salute.oneshot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salute.oneshot.global.util.NonceGenerator;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping("/my-page")
    public String getMyPage() {

        return "user/get-my-page";
    }

    @PreAuthorize("isAuthenticated()")

    @GetMapping("/information")
    public String updateUserInfo() {

        return "user/update-user-info";
    }
}
