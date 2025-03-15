package salute.oneshot.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/api/chats")
    public FindChatResponseDto findChat(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return chatService.findChat(userDetails.getId());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/api/admin/chats/{userId}")
    public FindChatResponseDto findChatForAdmin(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long userId
    ) {
        return chatService.findChatForAdmin(userDetails.getId(), userId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/api/admin/chats")
    public FindChatListResponseDto findChatListForAdmin(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(value = "cursor", required = false) String cursor,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        return chatService.findChatList(cursor, limit);
    }
}
