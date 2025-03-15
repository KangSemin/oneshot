package salute.oneshot.util;

import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ChatTestFactory {

    public static final Long SENDER_USER_ID = 1L;
    public static final Long RECEIVER_USER_ID = 2L;
    public static final String MESSAGE_SENDER = "user";
    public static final String MESSAGE_CONTENT = "메시지 입니다.";
    public static final Long MESSAGE_TIME_MILLIS = 1741314450785L;

    public static final String FORMATTED_MESSAGE = "u" + "::" + MESSAGE_CONTENT + "::" + MESSAGE_TIME_MILLIS;
    public static final String CURSOR = "";


    public static FindChatResponseDto createFindChatResponseDto() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<MessageResponseDto> messageList = List.of(createMessageResponseDto());

        Constructor<FindChatResponseDto> constructor =
                FindChatResponseDto.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        return constructor.newInstance(messageList);
    }

    public static FindChatListResponseDto createFindChatListResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<ChatPreviewResponseDto> chatList = List.of(createChatPreviewResponseDto());

        Constructor<FindChatListResponseDto> constructor =
                FindChatListResponseDto.class.getDeclaredConstructor(List.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(chatList, CURSOR);
    }

    public static MessageResponseDto createMessageResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<MessageResponseDto> constructor =
                MessageResponseDto.class.getDeclaredConstructor(String.class, String.class, Long.class);
        constructor.setAccessible(true);

        return constructor.newInstance(MESSAGE_SENDER, MESSAGE_CONTENT, MESSAGE_TIME_MILLIS);
    }

    private static ChatPreviewResponseDto createChatPreviewResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<ChatPreviewResponseDto> constructor =
                ChatPreviewResponseDto.class.getDeclaredConstructor(Long.class, String.class);
        constructor.setAccessible(true);

        return constructor.newInstance(UserTestFactory.USER_ID, FORMATTED_MESSAGE);
    }
}
