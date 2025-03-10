package salute.oneshot.util;

import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ChatTestFactory {

    public static final Long SENDER_USER_ID = 1L;
    public static final Long RECEIVER_USER_ID = 2L;
    public static final String MESSAGE_SENDER = "user";
    public static final String MESSAGE_CONTENT = "메시지 입니다.";
    public static final String MESSAGE_TIME_MILLIS = "1741314450785";

    public static final String FORMATTED_MESSAGE = "u" + "::" + MESSAGE_CONTENT + "::" + MESSAGE_TIME_MILLIS;
    public static final String CURSOR = "";


    public static FindChatResponseDto createFindChatResponseDto() {
        MessageResponseDto messageResponseDto = MessageResponseDto.from(FORMATTED_MESSAGE);

        List<MessageResponseDto> messageList = new ArrayList<>();
        messageList.add(messageResponseDto);

        return FindChatResponseDto.from(messageList);
    }

    public static FindChatListResponseDto createFindChatListResponseDto() {
        ChatPreviewResponseDto chatPreviewResponseDto = ChatPreviewResponseDto.of(Long.toString(RECEIVER_USER_ID), FORMATTED_MESSAGE);

        List<ChatPreviewResponseDto> chatList = new ArrayList<>();
        chatList.add(chatPreviewResponseDto);

        return FindChatListResponseDto.of(chatList, CURSOR);
    }
}
