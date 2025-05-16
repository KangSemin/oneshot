package salute.oneshot.domain.event.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.repository.EventRepository;
import salute.oneshot.global.exception.NotFoundException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventCompletionService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;
    private final EventNotificationService notificationService;

    @Transactional
    public void completeEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));

        event.changeEventStatus(EventStatus.ENDED);
        notificationService.notifyEventStatusChange(eventId, false);

        cleanupEventData(eventId, event);
    }

    public void cleanupEventData(Long eventId, Event event) {
        String counterKey = "event:" + eventId + ":counter";
        String limitKey = "event:" + eventId + ":limit";
        String winnersKey = "event:" + eventId + ":winners";

        Set<String> winners = redisTemplate.opsForSet().members(winnersKey);

        updateEventDetailWithWinners(event, winners);

        redisTemplate.delete(Arrays.asList(counterKey, limitKey, winnersKey));
    }

    private void updateEventDetailWithWinners(Event event, Set<String> winners) {
        EventDetail eventDetail = event.getEventDetail();
        String detailData = eventDetail.getDetailDataJson();

        try {
            JsonNode rootNode = objectMapper.readTree(detailData);
            ObjectNode objectNode = (ObjectNode) rootNode;
            ArrayNode winnersArray = objectMapper.createArrayNode();

            if (winners != null) {
                winners.forEach(winnersArray::add);
            }

            objectNode.set("winners", winnersArray);
            String updatedDetailData = objectMapper.writeValueAsString(objectNode);
            eventDetail.updateDetailData(updatedDetailData);
        } catch (Exception e) {
            throw new RuntimeException("이벤트 당첨자 정보 업데이트 실패", e);
        }
    }
}