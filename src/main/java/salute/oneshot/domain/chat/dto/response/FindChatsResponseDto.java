package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindChatsResponseDto {

    private final List<ChatPreviewResponseDto> chatList;
    private final String nextCursor;

    public static FindChatsResponseDto of(List<ChatPreviewResponseDto> chatList, String nextCursor) {
        return new FindChatsResponseDto(chatList, nextCursor);
    }
}
