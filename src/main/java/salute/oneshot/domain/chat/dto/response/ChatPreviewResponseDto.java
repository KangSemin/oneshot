package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatPreviewResponseDto {
    private final Long userId;
    private final String lastMessage;

    public static ChatPreviewResponseDto of(String userId, String lastMessage) {
        return new ChatPreviewResponseDto(Long.parseLong(userId), lastMessage);
    }
}
