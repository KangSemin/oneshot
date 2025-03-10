package salute.oneshot.domain.event.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.entity.EventResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class EventProducerService {

    @Value("${RABBITMQ_EXCHANGE_EVENT}")
    private String exchangeName;

    @Value("${RABBITMQ_ROUTING_KEY_EVENT}")
    private String routingKey;

    private final StringRedisTemplate redisTemplate;
    private final RabbitTemplate rabbitTemplate;
    private DefaultRedisScript<Long> eventParticipationScript;

    @PostConstruct
    public void loadScripts() {
        try {
            ClassPathResource resource = new ClassPathResource("scripts/event_limit.lua");
            String scriptContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            eventParticipationScript = new DefaultRedisScript<>();
            eventParticipationScript.setScriptText(scriptContent);
            eventParticipationScript.setResultType(Long.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Redis Lua script", e);
        }
    }

    public ParticipateEventDto processEventParticipation(
            Long eventId,
            String userId,
            Long couponId,
            String limitCount
    ) {
        log.info("프로듀서서비스 processEventParticipation 1");
        String counterKey = "event:" + eventId + ":counter";
        String limitKey = "event:" + eventId + ":limit";
        String winnersKey = "event:" + eventId + ":winners";

        log.info("프로듀서서비스 processEventParticipation 2");
        List<String> keysForScript = List.of(counterKey, limitKey, winnersKey);

        log.info("프로듀서서비스 processEventParticipation 3");
        Object[] args = { limitCount, userId };

        log.info("프로듀서서비스 processEventParticipation 3");
        Long result = redisTemplate
                .execute(eventParticipationScript, keysForScript, args);

        log.info("프로듀서서비스 processEventParticipation 4");
        return switch (result.intValue()) {
            case -1 -> getDto(eventId, userId, couponId, EventResult.ALREADY_PARTICIPATED);
            case 0 -> getDto(eventId, userId, couponId, EventResult.LIMIT_EXCEEDED);
            default -> getDto(eventId, userId, couponId, EventResult.WINNER);
        };
    }

    private ParticipateEventDto getDto(
            Long eventId,
            String userId,
            Long couponId,
            EventResult eventResult
    ) {
        log.info("프로듀서서비스 getDto");
        return ParticipateEventDto
                .of(eventId, Long.parseLong(userId), couponId, eventResult);
    }

    public void sendToEventQueue(ParticipateEventDto dto) {
        log.info("메시지 전송 시도: {}", dto);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, dto);
        log.info("메시지 전송 완료");
    }
}