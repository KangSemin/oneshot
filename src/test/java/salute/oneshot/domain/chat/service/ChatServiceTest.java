//package salute.oneshot.domain.chat.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.redis.core.ListOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
//import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
//import salute.oneshot.domain.user.entity.UserRole;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ChatServiceTest {
//
//    @Mock
//    RedisTemplate<String, String> redisTemplate;
//
//    @InjectMocks
//    ChatService chatService;
//
//    @Test
//    void findChat() {
//        // Given
//        Long userId = 2L;
//        // When
//        FindChatResponseDto result = chatService.findChat(userId);
//        // Then
//    }
//
//    @Test
//    void findChatForAdmin() {
//        // Given
//        Long adminId = 1L;
//        Long userId = 2L;
//        // When
//        FindChatResponseDto result = chatService.findChatForAdmin(adminId, userId);
//        // Then
//    }
//
//    @Test
//    void processMessageFromClient() {
//        // Given
//        // When
//        chatService.processMessageFromClient("메시지입니다.", "2", UserRole.USER);
//        // Then
//    }
//
//    @Test
//    void findChatList() {
//        // Given
//        String cursor;
//        int limit;
//
//        ListOperations<String, String> ops = Mockito.mock(ListOperations.class);
//        when(redisTemplate.opsForList()).thenReturn(ops);
//
//
//        when(ops.range(, , )).thenReturn(ops);
//
//        FindChatListResponseDto expected = FindChatListResponseDto.of();
//
//        // When
//        FindChatListResponseDto result = chatService.findChatList(cursor, limit);
//        // Then
//        assertThat(result)
//                .usingRecursiveComparison()
//                .ignoringFields("createdAt", "modifiedAt", "")
//                .isEqualTo(expected);
//    }
//
//
////    @Test
////    void 없는_ID로_리뷰_수정() {
////
////        // Given
////        ReviewUpdateRequestDto requestDto = new ReviewUpdateRequestDto("적당한 컨텐츠", 3, "properImgPath");
////
////        when(reviewRepository.findById(any(Long.class))).thenReturn(Optional.empty());
////
////        // When
////        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
////            reviewService.update(1L, requestDto);
////        });
////
////        // Then
//////        assertEquals("No such review id: 1", exception.getReason());
////        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorCode().getStatus());
////    }
//}