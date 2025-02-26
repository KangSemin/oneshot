package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.chat.dto.response.FindChatsResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

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

    @GetMapping("/api/admin/chats")
    public FindChatsResponseDto findChats(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        return chatService.findChats(cursor, limit);
    }
}
