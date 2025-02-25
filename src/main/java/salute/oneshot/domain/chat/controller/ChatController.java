package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.domain.user.entity.UserRole;

@Controller
//@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/sendToAdmin")
    public void sendMessageToAdmin(
            @Payload String message,
            SimpMessageHeaderAccessor headerAccessor
    ) {
//        CustomUserDetails userDetails = (CustomUserDetails) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("userDetails");
//        chatService.processMessageFromClient(message, userDetails.getId(), userDetails.getUserRole());
        chatService.processMessageFromClient(message, 2L, UserRole.USER);

        messagingTemplate.convertAndSend("/queue/admin", message);
    }

    @MessageMapping("/chat/reply/{userId}")
    public void replyToUser(
            @Payload String message,
            @DestinationVariable Long userId
    ) {

        chatService.processMessageFromClient(message, userId, UserRole.ADMIN);

        // TODO: userId uuid로 대체?
//        messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/chat", message);
        messagingTemplate.convertAndSend("/queue/chat/" + userId, message);
    }

//    @GetMapping("/api/chats")
//    public FindChatResponseDto findChat(
//            @AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        return chatService.findChat(userDetails.getId());
//    }
//
//    @PostMapping("/api/chats")
//    public void processMessageFromClient(
//            @RequestBody String message,
//            @AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        chatService.processMessageFromClient(message, userDetails.getId(), userDetails.getUserRole());
//    }
}
