package salute.oneshot.util;

import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ChatTestFactory {
    public static final Long USER_ID = 1L;



    public static FindChatResponseDto createFindChatResponseDto() {
        MessageResponseDto message = MessageResponseDto.from("u::메시지 입니다.::1741314450785");

        List<MessageResponseDto> messageList = new ArrayList<>();
        messageList.add(message);

        return FindChatResponseDto.from(messageList);
    }

    public static FindChatListResponseDto createFindChatListResponseDto() {
        long userId = 2L;
        String lastMessage = "u::가장 최근 메시지 입니다.::1741314450785";

        ChatPreviewResponseDto chatPreviewResponseDto = ChatPreviewResponseDto.of(Long.toString(userId), lastMessage);

        List<ChatPreviewResponseDto> chatList = new ArrayList<>();
        chatList.add(chatPreviewResponseDto);

        String nextCursor = "몰루";
        return FindChatListResponseDto.of(chatList, nextCursor);
    }
}
