package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.global.security.entity.CustomUserDetails;

import java.util.Objects;

//@Controller
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/receive")
    public void processMessageFromClient(
            @Payload String message,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("userDetails");
        chatService.processMessageFromClient(message, userDetails.getId(), userDetails.getUserRole());
    }

    @GetMapping("/api/chats")
    public FindChatResponseDto findChat(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return chatService.findChat(userDetails.getId());
    }

    @PostMapping("/api/chats")
    public void processMessageFromClient(
            @RequestBody String message,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        chatService.processMessageFromClient(message, userDetails.getId(), userDetails.getUserRole());
    }
}
