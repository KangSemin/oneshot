package salute.oneshot.domain.event.procssor;

import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventType;

public interface EventProcessor {
    // 이벤트 처리 로직
    void processEvent(Event event, EventDetail detail);
    // 입력받은 Object 데이터의 유효성을 검사
    void validateDetails(Object detailJson);
    // 변환 로직: 입력받은 Object → 각 이벤트에 맞는 EventData
    Object parseDetailData(Object detailJson);
    // 변환 로직: 입력받은 Object → JSON
    String convertToJson(Object data);
    // Event 팩토리에서 사용할 이벤트 타입 반환
    EventType getSupportedEventType();
}