package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindChatListResponseDto {

    private final List<ChatPreviewResponseDto> chatList;
    private final String nextCursor;

    public static FindChatListResponseDto of(List<ChatPreviewResponseDto> chatList, String nextCursor) {
        return new FindChatListResponseDto(chatList, nextCursor);
    }
}
