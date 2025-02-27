package salute.oneshot.domain.event.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.service.CreateEventSDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.procssor.EventProcessor;
import salute.oneshot.domain.event.procssor.EventProcessorFactory;
import salute.oneshot.domain.event.repository.EventRepository;

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

        EventProcessor processor =
                eventProcessorFactory.getProcessor(serviceDto.getEventType());
        String eventDetailJson =
                processor.convertToJson(serviceDto.getEventDetailJson());

        EventDetail eventDetail = EventDetail.of(event, eventDetailJson);
        event.addEventDetail(eventDetail);

        eventRepository.save(event);
        return EventBriefResponseDto.from(event);
    }
}
