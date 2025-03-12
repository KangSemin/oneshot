package salute.oneshot.domain.chat.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.MultiValueMap;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.util.ChatTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChatController.class)
@Import(TestSecurityConfig.class)
class ChatControllerTest extends AbstractRestDocsTests {

    private static final String API_TAG = "Chat API";

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
//                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.messageList[0].sender").value(ChatTestFactory.MESSAGE_SENDER))
                .andExpect(jsonPath("$.messageList[0].content").value(ChatTestFactory.MESSAGE_CONTENT))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(ChatTestFactory.MESSAGE_TIME_MILLIS))

                .andDo(document("chat/findChat",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("채팅 조회 성공")
                                .build())))
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
//                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.messageList[0].sender").value(ChatTestFactory.MESSAGE_SENDER))
                .andExpect(jsonPath("$.messageList[0].content").value(ChatTestFactory.MESSAGE_CONTENT))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(ChatTestFactory.MESSAGE_TIME_MILLIS))

                .andDo(document("chat/findChatForAdmin",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("어드민용 채팅 조회 성공")
                                .build())))
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
                        .queryParams(
                                MultiValueMap.fromSingleValue(
                                        Map.of(
                                                "cursor", "",
                                                "limit", "10"
                                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_RCP_RVW_SUCCESS))

                .andExpect(jsonPath("$.chatList[0].userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.chatList[0].lastMessage").value(ChatTestFactory.FORMATTED_MESSAGE))
                .andExpect(jsonPath("$.nextCursor").value(ChatTestFactory.CURSOR))

                .andDo(document("chat/findChatListForAdmin",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag(API_TAG)
                                .summary("어드민용 채팅방 리스트 조회 성공")
                                .queryParameters(
                                        parameterWithName("cursor").description("불러온 마지막 항목").optional(),
                                        parameterWithName("limit").description("로딩되는 항목 갯수").optional())
                                .build())))
                .andReturn();
    }
}