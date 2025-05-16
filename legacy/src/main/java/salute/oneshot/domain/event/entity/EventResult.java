package salute.oneshot.domain.event.entity;

import lombok.Getter;

@Getter
public enum EventResult {
    WINNER("당첨되었습니다."),
    ALREADY_PARTICIPATED("이미 참여한 이벤트입니다."),
    LIMIT_EXCEEDED("참여 인원이 마감되었습니다.");

    private final String message;

    EventResult(String message) {
        this.message = message;
    }
}
