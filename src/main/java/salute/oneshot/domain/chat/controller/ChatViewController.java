package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import salute.oneshot.global.config.NonceGenerator;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Controller
@RequiredArgsConstructor
public class ChatViewController {

    private final NonceGenerator nonceGenerator;

    @GetMapping("/chats")
    public String chatPage(
            Model model,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        model.addAttribute("userId", userDetails.getId());
        return "chat/chat";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/admin/chats/{userId}")
    public String adminChatPage(@PathVariable Long userId, Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        model.addAttribute("userId", userId);
        return "chat/admin-chat";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/admin/chats")
    public String adminChatListPage(Model model) {
        model.addAttribute("scriptNonce", nonceGenerator.getNonce());
        return "chat/admin-chat-list";
    }
}
