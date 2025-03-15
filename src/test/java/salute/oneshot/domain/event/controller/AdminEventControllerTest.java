package salute.oneshot.domain.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.ApiDocumentFactory;
import salute.oneshot.domain.common.ApiDocumentationLoader;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.event.dto.request.EventRequestDto;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.service.CreateEventSDto;
import salute.oneshot.domain.event.dto.service.UpdateEventSDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.service.EventService;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.EventTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@WebMvcTest(controllers = AdminEventController.class)
@Import(TestSecurityConfig.class)
class AdminEventControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EventService eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        event = EventTestFactory.createEvent();
    }

    @DisplayName("이벤트 생성 성공")
    @Test
    void successCreateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        EventBriefResponseDto responseDto =
                EventBriefResponseDto.from(event);

        given(eventService.createEvent(any(CreateEventSDto.class)))
                .willReturn(responseDto);
        // when & then
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_EVENT_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.name").value(EventTestFactory.NAME))
                .andExpect(jsonPath("$.data.startTime").value(EventTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(EventTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andDo(ApiDocumentFactory.listDoc(
                        "admin-event-controller-test/success-create-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_CREATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 생성 실패: 이벤트 종료 시간이 현재 시간보다 이전일 때")
    @Test
    void invalidEndTimeCreateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createInvalidEndEventRequestDto();

        // when & then
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EXPIRED_DATE.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/invalid-end-time-create-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_CREATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 생성 실패: 이벤트 시작 시간이 종료 시간보다 이후일 때")
    @Test
    void invalidStartTimeCreateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createInvalidStartEventRequestDto();

        // when & then
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_DATE.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/invalid-start-time-create-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_CREATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 생성 실패: 상세 정보 누락")
    @Test
    void missingDetailsCreateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.createEvent(any(CreateEventSDto.class)))
                .willThrow(new InvalidException(ErrorCode.MISSING_COUPON));

        // when & then
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.MISSING_COUPON.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/missing-details-create-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_CREATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 생성 실패: 이벤트 디테일 Object를 String으로 파싱 실패")
    @Test
    void failParsingCreateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.createEvent(any(CreateEventSDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_JSON_DATA));

        // when & then
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_JSON_DATA.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/fail-parsing-create-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_CREATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_CREATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 수정 성공")
    @Test
    void successUpdateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();
        EventDetailResponseDto responseDto =
                EventDetailResponseDto.of(event, EventTestFactory.EVENT_DETAIL_DATA);

        given(eventService.updateEvent(any(UpdateEventSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/admin/events/{eventId}", EventTestFactory.EVENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_EVENT_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.name").value(EventTestFactory.NAME))
                .andExpect(jsonPath("$.data.description").value(EventTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.startTime").value(EventTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(EventTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.eventDetail.couponId").value(1L))
                .andExpect(jsonPath("$.data.eventDetail.couponName").value("20% 할인 쿠폰"))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/success-update-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 수정 실패: 이벤트가 등록된 배너 정보가 존재하지 않을 경우")
    @Test
    void invalidBannerIdUpdateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.updateEvent(any(UpdateEventSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.BANNER_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/admin/events/{eventId}", EventTestFactory.EVENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.BANNER_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/invalid-banner-id-update-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 수정 실패: 쿠폰 정보 누락")
    @Test
    void missingDetailsUpdateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.updateEvent(any(UpdateEventSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.MISSING_COUPON));

        // when & then
        mockMvc.perform(patch("/api/admin/events/{eventId}", EventTestFactory.EVENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.MISSING_COUPON.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/missing-details-update-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 수정 실패: 이벤트 디테일 Object -> String 파싱 실패")
    @Test
    void failParsingUpdateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.updateEvent(any(UpdateEventSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.INVALID_JSON_DATA));

        // when & then
        mockMvc.perform(patch("/api/admin/events/{eventId}", EventTestFactory.EVENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_JSON_DATA.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/fail-parsing-update-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 수정 실패: 이벤트 디테일 String -> JsonNode 파싱 실패")
    @Test
    void failParsingJsonNodeUpdateEvent() throws Exception {
        // given
        EventRequestDto requestDto =
                EventTestFactory.createEventRequestDto();

        given(eventService.updateEvent(any(UpdateEventSDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_JSON_DATA));

        // when & then
        mockMvc.perform(patch("/api/admin/events/{eventId}", EventTestFactory.EVENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_JSON_DATA.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/fail-parsing-json-node-update-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 삭제 성공")
    @Test
    void successDeleteEvent() throws Exception {
        // given
        Long eventId = EventTestFactory.EVENT_ID;

        // when & then
        mockMvc.perform(delete("/api/admin/events/{eventId}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_EVENT_SUCCESS))
                .andExpect(jsonPath("$.data").value(eventId))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/success-delete-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_DELETE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_DELETE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 삭제 실패: 존재하지 않는 이벤트아이디로 삭제시도")
    @Test
    void invalidEventIdDeleteEvent() throws Exception {
        // given
        Long eventId = EventTestFactory.EVENT_ID;
        willThrow(new NotFoundException(ErrorCode.EVENT_NOT_FOUND))
                .given(eventService)
                .deleteEvent(eventId);

        // when & then
        mockMvc.perform(delete("/api/admin/events/{eventId}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EVENT_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/invalid-event-id-delete-event",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "ADMIN_EVENT_DELETE_API"),
                        ApiDocumentationLoader.getDescription("event", "ADMIN_EVENT_DELETE_API")))
                .andReturn();
    }

    @DisplayName("이벤트 GET 화면에서 스트림 구독, Emitter 반환 성공")
    @Test
    void successStreamEventUpdates() throws Exception {
        // Given
        Long eventId = EventTestFactory.EVENT_ID;
        SseEmitter emitter = new SseEmitter();

        given(eventService.subscribeEvent(eventId))
                .willReturn(emitter);

        // When & Then
        mockMvc.perform(get("/api/admin/events/event-stream/{eventId}", eventId)
                        .accept(MediaType.TEXT_EVENT_STREAM_VALUE)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andDo(ApiDocumentFactory.listDoc(
                        "event/success-stream-event-updates",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "EVENT_STREAM_API"),
                        ApiDocumentationLoader.getDescription("event", "EVENT_STREAM_API")))
                .andReturn();

        then(eventService).should().subscribeEvent(eventId);
    }

    @DisplayName("이벤트 GET 화면에서 스트림 구독, Emitter 반환 실패: 존재하지 않는 이벤트아이디")
    @Test
    void invalidEventIdStreamEventUpdates() throws Exception {
        // Given
        Long eventId = EventTestFactory.EVENT_ID;

        given(eventService.subscribeEvent(eventId))
                .willThrow(new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        // When & Then
        mockMvc.perform(get("/api/admin/events/event-stream/{eventId}", eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockAdminDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EVENT_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "event/invalid-event-id-stream-event-updates",
                        ApiDocumentFactory.EVENT_TAG,
                        ApiDocumentationLoader.getSummary("event", "EVENT_STREAM_API"),
                        ApiDocumentationLoader.getDescription("event", "EVENT_STREAM_API")))
                .andReturn();
    }
}