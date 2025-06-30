package salute.oneshot.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.MessageResponseDto;
import salute.oneshot.domain.user.entity.UserRole;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public FindChatResponseDto findChatForAdmin(Long adminId, Long userId) {
//        validateAdmin();
        return findChat(userId);
    }

    public void processMessageFromClient(String message, String userId, UserRole role) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        String key = CHAT_KEY_PREFIX + userId;

        String messagePrefix = (role == UserRole.USER) ? "u" : "a";
        String formattedMessage = messagePrefix + "::" + message + "::" + System.currentTimeMillis();
        ops.rightPush(key, formattedMessage);

        redisTemplate.expire(key, Duration.ofDays(3));
        ops.trim(key, -MAX_CHAT_SIZE, -1);

        // 챗 메타데이터
        long now = System.currentTimeMillis();
        String metaValue = userId + "::" + message + "::" + now;
        redisTemplate.opsForZSet().add("chatList", metaValue, now);
    }

    public FindChatListResponseDto findChatList(String cursor, int limit) {
        // 3일 전 타임스탬프 계산
        long threeDaysAgo = System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000;

        // 오래된 메타데이터 제거: 3일 이전의 score를 가진 모든 항목 삭제
        redisTemplate.opsForZSet().removeRangeByScore("chatList", 0, threeDaysAgo);

        // 남은 전체 데이터를 조회 (정렬 상태가 유지된 상태로 반환)
        Set<ZSetOperations.TypedTuple<String>> recentChatMetadata =
                redisTemplate.opsForZSet().rangeWithScores("chatList", 0, -1);

        // TODO: 레디스가 초기화되고 한번도 채팅이 저장되지 않은 경우?
        if (recentChatMetadata == null) {
//            throw new Exception();
        };

        List<ChatPreviewResponseDto> chatList = recentChatMetadata.stream().map(t -> t.getValue()).map(ChatPreviewResponseDto::from).toList();

        // TODO: 커서 페이징 구현
        String nextCursor = "";

        return FindChatListResponseDto.of(chatList, nextCursor);
    }
}
