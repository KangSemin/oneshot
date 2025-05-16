package salute.oneshot.domain.event.procssor;

import org.springframework.stereotype.Component;
import salute.oneshot.domain.event.entity.EventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventProcessorFactory {

    private final Map<EventType, EventProcessor> processors;

    public EventProcessorFactory(List<EventProcessor> allProcessors) {
        processors = new HashMap<>();
        for (EventProcessor processor : allProcessors) {
            processors.put(processor.getSupportedEventType(), processor);
        }
    }

    // 등록된 EventProcessor 중 이벤트 타입과 일치하는 Processor 반환
    public EventProcessor getProcessor(EventType eventType) {
        return processors.get(eventType);
    }
}