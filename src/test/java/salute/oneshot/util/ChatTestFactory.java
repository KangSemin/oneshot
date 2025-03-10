package salute.oneshot.util;

import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ChatTestFactory {

    public static final Long USER_ID = 1L;
    public static final String RAW_MESSAGE = "u::메시지 입니다.::1741314450785";

    public static FindChatResponseDto createFindChatResponseDto() {
        MessageResponseDto message = MessageResponseDto.from(RAW_MESSAGE);

        List<MessageResponseDto> messageList = new ArrayList<>();
        messageList.add(message);

        return FindChatResponseDto.from(messageList);
    }

    public static FindChatListResponseDto createFindChatListResponseDto() {
        long userId = 2L;

        ChatPreviewResponseDto chatPreviewResponseDto = ChatPreviewResponseDto.of(Long.toString(userId), RAW_MESSAGE);

        List<ChatPreviewResponseDto> chatList = new ArrayList<>();
        chatList.add(chatPreviewResponseDto);

        String nextCursor = "";
        return FindChatListResponseDto.of(chatList, nextCursor);
    }
}
