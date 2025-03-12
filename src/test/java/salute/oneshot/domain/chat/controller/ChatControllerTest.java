package salute.oneshot.domain.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.util.ChatTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChatController.class)
@Import(TestSecurityConfig.class)
class ChatControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChatService chatService;

    @DisplayName("채팅 조회 성공")
    @Test
    void successFindChat() throws Exception {
        // given
        FindChatResponseDto responseDto = ChatTestFactory.createFindChatResponseDto();

        given(chatService.findChat(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/chats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageList[0].sender").value(ChatTestFactory.MESSAGE_SENDER))
                .andExpect(jsonPath("$.messageList[0].content").value(ChatTestFactory.MESSAGE_CONTENT))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(ChatTestFactory.MESSAGE_TIME_MILLIS))
                .andReturn();
    }

    @DisplayName("어드민용 채팅 조회 성공")
    @Test
    void successFindChatForAdmin() throws Exception {
        // given
        FindChatResponseDto responseDto = ChatTestFactory.createFindChatResponseDto();

        given(chatService.findChatForAdmin(any(Long.class), any(Long.class)))
                .willReturn(responseDto);

        // when & then
        // userId를 sender와 receiver로 나누는 것도 가능
        mockMvc.perform(get("/api/admin/chats/{userId}", UserTestFactory.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageList[0].sender").value(ChatTestFactory.MESSAGE_SENDER))
                .andExpect(jsonPath("$.messageList[0].content").value(ChatTestFactory.MESSAGE_CONTENT))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(ChatTestFactory.MESSAGE_TIME_MILLIS))
                .andReturn();
    }

    @DisplayName("어드민용 채팅방 리스트 조회 성공")
    @Test
    void successFindChatListForAdmin() throws Exception {
        // given
        FindChatListResponseDto responseDto = ChatTestFactory.createFindChatListResponseDto();

        given(chatService.findChatList(nullable(String.class), anyInt()))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/admin/chats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatList[0].userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.chatList[0].lastMessage").value(ChatTestFactory.FORMATTED_MESSAGE))
                .andExpect(jsonPath("$.nextCursor").value(ChatTestFactory.CURSOR))
                .andReturn();
    }
}