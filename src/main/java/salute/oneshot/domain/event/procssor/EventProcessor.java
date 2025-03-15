package salute.oneshot.domain.event.procssor;

import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventType;

public interface EventProcessor {
    // 이벤트 처리 로직
    EventDetail processEvent(Event event, Object eventDetailData);
    // 이벤트 참여 로직
    ParticipateEventDto participateEvent(Long eventId, Long userId, String detailDataJson, int limitCount);
    // 입력받은 Object 데이터의 유효성을 검사
    void validateEventDetail(Object eventDetailData);
    // 변환 로직: 입력받은 Object → 각 이벤트에 맞는 EventData
    Object parseEventDetail(Object eventDetailData);
    // 변환 로직: 입력받은 Object → JSON
    String convertEventDetailToJson(Object eventDetailData);
    // Event 팩토리에서 사용할 이벤트 타입 반환
    EventType getSupportedEventType();
}