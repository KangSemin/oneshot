package salute.oneshot.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.chat.dto.response.ChatPreviewResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
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

    public FindChatResponseDto findChatForAdmin(Long adminId, Long userId) {
//        validateAdmin();
        return findChat(userId);
    }

    public void processMessageFromClient(String message, String userId, UserRole role) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        String key = CHAT_KEY_PREFIX + userId;

        String messagePrefix = (role == UserRole.USER) ? "u::" : "a::";
        String formattedMessage = messagePrefix + message + "::" + System.currentTimeMillis();
        ops.rightPush(key, formattedMessage);

        redisTemplate.expire(key, Duration.ofDays(3));
        ops.trim(key, -MAX_CHAT_SIZE, -1);
    }

    public FindChatListResponseDto findChatList(String cursor, int limit) {
        // 커서가 없으면 "0"으로 설정 (SCAN 시작)
        String scanCursor = (cursor == null || cursor.isEmpty()) ? "0" : cursor;

        return redisTemplate.execute((RedisCallback<FindChatListResponseDto>) connection -> {
            List<ChatPreviewResponseDto> chatList = new ArrayList<>();

            // Lettuce 환경: Spring Data Redis의 scan 사용 (Cursor<byte[]>에는 native 커서가 없음)
            ScanOptions options = ScanOptions.scanOptions().match("chat::*").count(limit).build();
            Cursor<byte[]> cursorObj = connection.scan(options);

            // limit 개수만큼 키를 읽음
            while (cursorObj.hasNext() && chatList.size() < limit) {
                byte[] keyBytes = cursorObj.next();
                String key = new String(keyBytes);
                // 키 형식: "chat::{userId}" -> "::" 기준 분리
                String[] parts = key.split("::");
                String userId = parts[1];
                // 해당 키의 리스트에서 마지막 요소를 가져옴
                String lastMessage = redisTemplate.opsForList().index(key, -1);
                chatList.add(ChatPreviewResponseDto.of(userId, lastMessage));
            }
            // Lettuce Cursor는 native 커서를 제공하지 않으므로, 추가 데이터가 있는지 여부를 별도로 처리해야 합니다.
            // 여기서는 단순히 다음 커서를 빈 문자열("")로 설정합니다.
            String nextCursor = "";

            try {
                cursorObj.close();
            } catch (Exception e) {
                // 예외는 로깅 또는 무시
            }
            return FindChatListResponseDto.of(chatList, nextCursor);
        });
    }
}
