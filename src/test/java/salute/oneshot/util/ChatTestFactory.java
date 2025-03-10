package salute.oneshot.util;

import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ChatTestFactory {

    public static final Long USER_ID = 2L;
    public static final String RAW_MESSAGE = "u::메시지 입니다.::1741314450785";

    public static FindChatResponseDto createFindChatResponseDto() {
        MessageResponseDto messageResponseDto = MessageResponseDto.from(RAW_MESSAGE);

        List<MessageResponseDto> messageList = new ArrayList<>();
        messageList.add(messageResponseDto);

        return FindChatResponseDto.from(messageList);
    }

    public static FindChatListResponseDto createFindChatListResponseDto() {
        ChatPreviewResponseDto chatPreviewResponseDto = ChatPreviewResponseDto.of(Long.toString(USER_ID), RAW_MESSAGE);

        List<ChatPreviewResponseDto> chatList = new ArrayList<>();
        chatList.add(chatPreviewResponseDto);

        String nextCursor = "";
        return FindChatListResponseDto.of(chatList, nextCursor);
    }
}
