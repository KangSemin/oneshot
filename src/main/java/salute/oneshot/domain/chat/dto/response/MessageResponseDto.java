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


    public static MessageResponseDto from(String formattedMessage) {
        int contentEnd = formattedMessage.lastIndexOf("::");

        String sender = (formattedMessage.charAt(0) == 'u') ? "user" : "admin";
        String content = formattedMessage.substring(3, contentEnd);
        Long timeMillis = Long.parseLong(formattedMessage.substring(contentEnd + 2));

        return new MessageResponseDto(sender, content, timeMillis);
    }
}
