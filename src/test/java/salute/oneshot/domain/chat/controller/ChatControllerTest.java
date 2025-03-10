package salute.oneshot.domain.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.chat.dto.response.FindChatListResponseDto;
import salute.oneshot.domain.chat.dto.response.FindChatResponseDto;
import salute.oneshot.domain.chat.service.ChatService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.global.security.model.CustomUserDetails;
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
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private JwtProvider jwtProvider;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private ChatService chatService;

//    @MockitoBean
//    private RedisTemplate<String, String> redisTemplate;

//    @BeforeEach
//    void setUp() {
//        address = AddressTestFactory.createAddress();
//    }

    @DisplayName("채팅 조회 성공")
    @Test
    void successFindChat() throws Exception {
        // given
//        CreateAddressRequestDto requestDto =
//                AddressTestFactory.createCreateRequestDto();
        FindChatResponseDto responseDto = ChatTestFactory.createFindChatResponseDto();

        given(chatService.findChat(any(Long.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/chats")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageList[0].sender").value(responseDto.getMessageList().get(0).getSender()))
                .andExpect(jsonPath("$.messageList[0].content").value(responseDto.getMessageList().get(0).getContent()))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(responseDto.getMessageList().get(0).getTimeMillis()))
//                .andExpect(jsonPath("$.messageList").value(objectMapper.writeValueAsString(responseDto.getMessageList())))
//                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_ADR_SUCCESS))
//                .andExpect(jsonPath("$.data.addressName").value(AddressTestFactory.ADDRESS_NAME))
//                .andExpect(jsonPath("$.data.postAddress").value(AddressTestFactory.POST_ADDRESS))
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
        mockMvc.perform(get("/api/admin/chats/" + 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageList[0].sender").value(responseDto.getMessageList().get(0).getSender()))
                .andExpect(jsonPath("$.messageList[0].content").value(responseDto.getMessageList().get(0).getContent()))
                .andExpect(jsonPath("$.messageList[0].timeMillis").value(responseDto.getMessageList().get(0).getTimeMillis()))
                .andReturn();
    }

    @DisplayName("어드민용 채팅방 조회 성공")
    @Test
    void successFindChatListForAdmin() throws Exception {
        // given
        FindChatListResponseDto responseDto = ChatTestFactory.createFindChatListResponseDto();

        given(chatService.findChatList(nullable(String.class), anyInt()))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/admin/chats")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(user(UserTestFactory.createMockUserDetails())))
                        .with(user(CustomUserDetails.of(1L, UserRole.SUPER_ADMIN))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatList[0].userId").value(responseDto.getChatList().get(0).getUserId()))
                .andExpect(jsonPath("$.chatList[0].lastMessage").value(responseDto.getChatList().get(0).getLastMessage()))
                .andExpect(jsonPath("$.nextCursor").value(responseDto.getNextCursor()))
                .andReturn();
    }
}