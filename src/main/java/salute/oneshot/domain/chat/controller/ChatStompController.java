package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.domain.user.entity.UserRole;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatStompController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/sendToAdmin")
    public void sendMessageToAdmin(
            @Payload String message,
            Principal principal
    ) {
        chatService.processMessageFromClient(message, principal.getName(), UserRole.USER);

        messagingTemplate.convertAndSend("/queue/admin", message);
    }

    @MessageMapping("/chat/sendToUser/{userId}")
    public void sendMessageToUser(
            @Payload String message,
            @DestinationVariable Long userId
    ) {
        chatService.processMessageFromClient(message, userId.toString(), UserRole.ADMIN);

        // TODO: userId uuid로 대체?
//        messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/chat", message);
        messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/chat", message);
//        messagingTemplate.convertAndSend("/queue/chat/" + userId, message);
    }
}
