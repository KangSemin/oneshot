package salute.oneshot.domain.chat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindChatResponseDto {

    private final List<MessageResponseDto> messageList;

    public static FindChatResponseDto from(List<MessageResponseDto> messageList) {
        return new FindChatResponseDto(messageList);
    }
}
