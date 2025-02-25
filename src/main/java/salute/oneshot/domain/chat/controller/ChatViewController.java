package salute.oneshot.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatViewController {

    @GetMapping("/admin/chat")
    public String adminChat() {
        return "admin-chat";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
