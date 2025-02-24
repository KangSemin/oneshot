package salute.oneshot.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;
import salute.oneshot.domain.user.entity.UserRole;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RedisTemplate<String, String> redisTemplate;

    private final String CHAT_KEY_PREFIX = "chat::";
    private final int MAX_CHAT_SIZE = 100;

    public FindChatResponseDto findChat(Long userId) {
        String key = CHAT_KEY_PREFIX + userId;
//        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
//
//        }

        ListOperations<String, String> ops = redisTemplate.opsForList();

        List<String> messageStringList = ops.range(key, 0, -1);

        if (messageStringList == null) {
            return FindChatResponseDto.from(new ArrayList<MessageResponseDto>());
        }

        List<MessageResponseDto> messageList = messageStringList
                .stream()
                .map(MessageResponseDto::from)
                .toList();
        return FindChatResponseDto.from(messageList);
    }

    public void processMessageFromClient(String message, Long userId, UserRole role) {
//        message;
        ListOperations<String, String> ops = redisTemplate.opsForList();
        String key = CHAT_KEY_PREFIX + userId;

        String messagePrefix = role == UserRole.USER ? "u::" : "a::";
        ops.rightPush(key, messagePrefix + message + "::" + System.currentTimeMillis());

        redisTemplate.expire(key, Duration.ofDays(3));

        Long size = ops.size(key);
        if (size != null && size > MAX_CHAT_SIZE) {
            ops.trim(key, -MAX_CHAT_SIZE, -1);
        }
    }
}
