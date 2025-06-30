package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatPreviewResponseDto {
    private final String userId;
    private final String lastMessage;
    private final String timeMillis;

    // TODO: 다른 메소드로 리팩터링
//    public static ChatPreviewResponseDto of(String userId, String lastMessage) {
//        return new ChatPreviewResponseDto(Long.parseLong(userId), lastMessage);
//    }


    public static ChatPreviewResponseDto from(String formattedMessage) {
        int contentStart = formattedMessage.indexOf("::");
        int contentEnd = formattedMessage.lastIndexOf("::");

        String userId = formattedMessage.substring(0, contentStart);
        String lastMessage = formattedMessage.substring(contentStart + 2, contentEnd);
        String timeMillis = formattedMessage.substring(contentEnd + 2);

        return new ChatPreviewResponseDto(userId, lastMessage, timeMillis);
    }
}
