package salute.oneshot.domain.event.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import salute.oneshot.domain.banner.entity.Banner;
import salute.oneshot.domain.banner.repository.BannerRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.response.EventPageResponseDto;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.dto.service.CreateEventSDto;
import salute.oneshot.domain.event.dto.service.GetEventsSDto;
import salute.oneshot.domain.event.dto.service.ParticipateEventSDto;
import salute.oneshot.domain.event.dto.service.UpdateEventSDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.procssor.EventProcessor;
import salute.oneshot.domain.event.procssor.EventProcessorFactory;
import salute.oneshot.domain.event.repository.EventRepository;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventNotificationService notificationService;
    private final BannerRepository bannerRepository;
    private final EventRepository eventRepository;
    private final EventProcessorFactory eventProcessorFactory;
    private final ObjectMapper objectMapper;

    @Transactional
    public EventBriefResponseDto createEvent(CreateEventSDto serviceDto) {
        Event event = Event.of(
                serviceDto.getName(),
                serviceDto.getDescription(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                serviceDto.getEventType(),
                serviceDto.getLimitCount());

        EventProcessor processor = eventProcessorFactory
                .getProcessor(serviceDto.getEventType());
        EventDetail eventDetail = processor
                .processEvent(event, serviceDto.getEventDetailData());
        event.addEventDetail(eventDetail);

        eventRepository.save(event);

        return EventBriefResponseDto.from(event);
    }

    @Transactional
    public EventDetailResponseDto updateEvent(UpdateEventSDto serviceDto) {
        Banner banner = bannerRepository.findByEventId(serviceDto.getEventId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.BANNER_NOT_FOUND));
        Event event = banner.getEvent();

        EventProcessor processor = eventProcessorFactory
                .getProcessor(serviceDto.getEventType());
        processor.validateEventDetail(serviceDto.getEventDetailData());

        String eventDetailJson = processor
                .convertEventDetailToJson(serviceDto.getEventDetailData());

        event.updateEvent(
                serviceDto.getName(),
                serviceDto.getEventType(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                eventDetailJson);

        Object eventDetail = parseJsonToNode(eventDetailJson);
        return EventDetailResponseDto.of(event, eventDetail);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        if (eventRepository.deleteEventById(eventId) != 1) {
            throw new NotFoundException(ErrorCode.EVENT_NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public EventDetailResponseDto getEvent(Long eventId) {
        Event event = getEventById(eventId);

        Object eventDetail =
                parseJsonToNode(event.getEventDetail().getDetailDataJson());

        return EventDetailResponseDto.of(event, eventDetail);
    }

    @Transactional(readOnly = true)
    public EventPageResponseDto getEvents(GetEventsSDto serviceDto) {
        Page<Event> events = eventRepository.findEvents(
                serviceDto.getStatus(),
                serviceDto.getType(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                serviceDto.getPageable());

        Page<EventBriefResponseDto> eventPage =
                events.map(EventBriefResponseDto::from);

        return EventPageResponseDto.from(eventPage);
    }

    @Transactional
    public ParticipateEventDto participateEvent(ParticipateEventSDto serviceDto) {
        Event event = eventRepository.findById(serviceDto.getEventId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        if (!event.getStatus().equals(EventStatus.ONGOING)) {
            throw new InvalidException(ErrorCode.INVALID_EVENT_STATUS);
        }

        EventProcessor processor = eventProcessorFactory
                .getProcessor(event.getEventType());

        return processor.participateEvent(
                event.getId(),
                serviceDto.getUserId(),
                event.getEventDetail().getDetailDataJson(),
                event.getLimitCount());
    }

    public SseEmitter subscribeEvent(Long eventId) {
        EventStatus status = eventRepository.findStatusById(eventId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        return notificationService.subscribeEvent(eventId, status);
    }

    @Transactional
    public void activateEvent(Long eventId) {
        if (eventRepository.updateEventStatus(eventId, EventStatus.UPCOMING, EventStatus.ONGOING) > 0) {
            notificationService.notifyEventStatusChange(eventId, true);
            log.info("이벤트 ID: {} 활성화 성공", eventId);
        } else {
            log.warn("이벤트 ID: {} 활성화 실패", eventId);
        }
    }

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }

    private JsonNode parseJsonToNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: {}", e.getMessage(), e);
            throw new InvalidException(ErrorCode.INVALID_JSON_DATA);
        }
    }
}