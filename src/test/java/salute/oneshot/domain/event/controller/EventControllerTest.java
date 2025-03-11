package salute.oneshot.domain.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.response.EventPageResponseDto;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.dto.service.GetEventsSDto;
import salute.oneshot.domain.event.dto.service.ParticipateEventSDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventResult;
import salute.oneshot.domain.event.service.EventService;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.util.CouponTestFactory;
import salute.oneshot.util.EventTestFactory;
import salute.oneshot.util.UserTestFactory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@Import(TestSecurityConfig.class)
class EventControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EventService eventService;

    private Event event;
    private User user;

    @BeforeEach
    void setUp() {
        event = EventTestFactory.createEvent();
        user = UserTestFactory.createUser();
    }

    @DisplayName("이벤트 목록 조회 성공(endTime 오름차순)")
    @Test
    void successGetEvents() throws Exception{
        // given
        Event event2 = EventTestFactory.createEvent2();
        Event event3 = EventTestFactory.createEvent3();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event2),
                EventBriefResponseDto.from(event),
                EventBriefResponseDto.from(event3));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                .param("page", "1")
                .param("size", "10")
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(2L))
                .andExpect(jsonPath("$.data.events[1].id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.events[2].id").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 상태 조건 입력(ONGOING) ")
    @Test
    void successGetEventsWithStatus() throws Exception{
        // given
        Event event3 = EventTestFactory.createEvent3();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event3));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 타입 조건 입력(선착순 FCFS)")
    @Test
    void successGetEventsWithType() throws Exception{
        // given
        Event event3 = EventTestFactory.createEvent3();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event),
                EventBriefResponseDto.from(event3));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.events[1].id").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 시작일 입력")
    @Test
    void successGetEventsWithStartDate() throws Exception{
        // given
        Event event3 = EventTestFactory.createEvent3();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event),
                EventBriefResponseDto.from(event3));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2025-03-08")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.events[1].id").value(3L))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 종료일 입력")
    @Test
    void successGetEventsWithEndDate() throws Exception{
        // given
        Event event2 = EventTestFactory.createEvent2();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event),
                EventBriefResponseDto.from(event2));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .param("endDate", "2025-03-09")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.events[1].id").value(2L))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 시작일 & 종료일 입력")
    @Test
    void successGetEventsWithStartAndEndDate() throws Exception{
        // given
        Event event3 = EventTestFactory.createEvent3();

        List<EventBriefResponseDto> events = List.of(
                EventBriefResponseDto.from(event3));

        PageImpl<EventBriefResponseDto> page = new PageImpl<>(events);

        EventPageResponseDto responseDto =
                EventPageResponseDto.from(page);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .param("startDate", "2025-03-09")
                        .param("endDate", "2025-03-10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events[0].id").value(3L))
                .andReturn();
    }

    @DisplayName("이벤트 목록 조회 성공: 빈 목록 조회")
    @Test
    void successGetEventsWithEmpty() throws Exception{
        // given
        List<EventBriefResponseDto> emptyEvents = List.of();
        PageImpl<EventBriefResponseDto> emptyPage = new PageImpl<>(emptyEvents);
        EventPageResponseDto responseDto =
                EventPageResponseDto.from(emptyPage);

        given(eventService.getEvents(any(GetEventsSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.events").isArray())
                .andExpect(jsonPath("$.data.events").isEmpty())
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andReturn();
    }

    @DisplayName("이벤트 단건 조회 성공")
    @Test
    void successGetEvent() throws Exception {
        // given
        EventDetailResponseDto responseDto =
                EventDetailResponseDto.of(event, EventTestFactory.EVENT_DETAIL_DATA);

        given(eventService.getEvent(EventTestFactory.EVENT_ID))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/events/{eventId}", EventTestFactory.EVENT_ID)
                .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_EVT_SUCCESS))
                .andExpect(jsonPath("$.data.id").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.name").value(EventTestFactory.NAME))
                .andExpect(jsonPath("$.data.description").value(EventTestFactory.DESCRIPTION))
                .andExpect(jsonPath("$.data.startTime").value(EventTestFactory.START_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.endTime").value(EventTestFactory.END_LOCAL_DATE_TIME.toString()))
                .andExpect(jsonPath("$.data.eventDetail.couponId").value(1L))
                .andExpect(jsonPath("$.data.eventDetail.couponName").value("20% 할인 쿠폰"))
                .andReturn();
    }

    @DisplayName("이벤트 단건 조회 실패: 존재하지 않는 이벤트 아이디로 조회")
    @Test
    void invalidEventIdGetEvent() throws Exception {
        // given
        given(eventService.getEvent(EventTestFactory.EVENT_ID))
                .willThrow(new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        // when & then
        mockMvc.perform(get("/api/events/{eventId}", EventTestFactory.EVENT_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.EVENT_NOT_FOUND.getMessage()))
                .andReturn();
    }

    @DisplayName("이벤트 단건 조회 실패: 이벤트 디테일 String -> JsonNode 파싱 실패")
    @Test
    void failParsingJsonNodeGetEvent() throws Exception {
        // given
        given(eventService.getEvent(EventTestFactory.EVENT_ID))
                .willThrow(new InvalidException(ErrorCode.INVALID_JSON_DATA));

        // when & then
        mockMvc.perform(get("/api/events/{eventId}", EventTestFactory.EVENT_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_JSON_DATA.getMessage()))
                .andReturn();
    }

    @DisplayName("이벤트 참여 성공")
    @Test
    void successParticipateEvent() throws Exception {
        // given
        ParticipateEventDto responseDto = ParticipateEventDto.of(
                        event.getId(),
                        user.getId(),
                        CouponTestFactory.COUPON_ID,
                        EventResult.WINNER);

        given(eventService.participateEvent(any(ParticipateEventSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/events/{eventId}/participation", EventTestFactory.EVENT_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.PRT_EVENT_SUCCESS))
                .andExpect(jsonPath("$.data.eventId").value(EventTestFactory.EVENT_ID))
                .andExpect(jsonPath("$.data.userId").value(UserTestFactory.USER_ID))
                .andExpect(jsonPath("$.data.couponId").value(CouponTestFactory.COUPON_ID))
                .andExpect(jsonPath("$.data.eventResult").value(EventResult.WINNER.toString()))
                .andReturn();
    }

    @DisplayName("이벤트 참여 실패: 쿠폰상태가 'ONGOING'이 아닌 경우")
    @Test
    void invalidStatusParticipateEvent() throws Exception {
        // given
        given(eventService.participateEvent(any(ParticipateEventSDto.class)))
                .willThrow(new InvalidException(ErrorCode.INVALID_EVENT_STATUS));

        // when & then
        mockMvc.perform(post("/api/events/{eventId}/participation", EventTestFactory.EVENT_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.INVALID_EVENT_STATUS.getMessage()))
                .andReturn();
    }

    @DisplayName("이벤트 참여 실패: 쿠폰 정보 누락")
    @Test
    void missingDetailsParticipateEvent() throws Exception {
        // given
        given(eventService.participateEvent(any(ParticipateEventSDto.class)))
                .willThrow(new InvalidException(ErrorCode.MISSING_COUPON));

        // when & then
        mockMvc.perform(post("/api/events/{eventId}/participation", EventTestFactory.EVENT_ID)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.MISSING_COUPON.getMessage()))
                .andReturn();
    }
}