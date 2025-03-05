package salute.oneshot.domain.event.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.response.EventPageResponseDto;
import salute.oneshot.domain.event.dto.service.CreateEventSDto;
import salute.oneshot.domain.event.dto.service.GetEventsSDto;
import salute.oneshot.domain.event.dto.service.UpdateEventSDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.procssor.EventProcessor;
import salute.oneshot.domain.event.procssor.EventProcessorFactory;
import salute.oneshot.domain.event.repository.EventRepository;
import salute.oneshot.global.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventProcessorFactory eventProcessorFactory;
    private final ObjectMapper objectMapper;

    @Transactional
    public EventBriefResponseDto createEvent(CreateEventSDto serviceDto) {
        Event event = Event.of(
                serviceDto.getName(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                serviceDto.getEventType());

        EventProcessor processor = eventProcessorFactory
                .getProcessor(serviceDto.getEventType());
        processor.validateEventDetail(serviceDto.getEventDetail());

        String eventDetailJson = processor
                .convertEventDetailToJson(serviceDto.getEventDetail());
        EventDetail eventDetail = EventDetail.of(event, eventDetailJson);
        event.addEventDetail(eventDetail);

        eventRepository.save(event);
        return EventBriefResponseDto.from(event);
    }

    @Transactional
    public EventDetailResponseDto updateEvent(UpdateEventSDto serviceDto) {
        Event event = getEventById(serviceDto.getEventId());

        EventProcessor processor = eventProcessorFactory
                .getProcessor(serviceDto.getEventType());
        processor.validateEventDetail(serviceDto.getEventDetail());

        String eventDetailJson = processor
                .convertEventDetailToJson(serviceDto.getEventDetail());

        event.updateEvent(
                serviceDto.getName(),
                serviceDto.getEventType(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                eventDetailJson);

        Object eventDetail =
                parseJsonToNode(eventDetailJson);

        return EventDetailResponseDto.of(event, eventDetail);
    }

    @Transactional
    public Long deleteEvent(Long eventId) {
        if (eventRepository.deleteEventById(eventId) == 1) {
            return eventId;
        }
        throw new NotFoundException(ErrorCode.COUPON_NOT_FOUND);
    }

    @Transactional(readOnly = true)
    public EventDetailResponseDto getEvent(Long eventId) {
        Event event = getEventById(eventId);

        Object eventDetail =
                parseJsonToNode(event.getEventDetail().getDetailData());

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

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
    }

    // DB에 저장된 JSON 계층 구조로 변환
    private JsonNode parseJsonToNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: {}", e.getMessage(), e);
            throw new RuntimeException("JSON 파싱 오류", e);
        }
    }
}
