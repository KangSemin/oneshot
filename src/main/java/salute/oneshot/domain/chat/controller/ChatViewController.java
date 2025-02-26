package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import salute.oneshot.global.config.NonceGenerator;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Controller
@RequiredArgsConstructor
public class ChatViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping("/admin/chat")
    public String adminChat() {
        return "admin-chat";
    }

    @GetMapping("/chat")
    public String chat(
            Model model,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        model.addAttribute("userId", userDetails.getId());
        return "chat";
    }
}
