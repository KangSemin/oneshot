package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResponseDto {

    private final String sender;
    private final String content;
    private final Long timeMillis;


    public static MessageResponseDto from(String message) {
        int contentEnd = message.lastIndexOf("::");

        String sender = (message.charAt(0) == 'u') ? "user" : "admin";
        String content = message.substring(3, contentEnd);
        Long timeMillis = Long.parseLong(message.substring(contentEnd + 2));

        return new MessageResponseDto(sender, content, timeMillis);
    }
}
