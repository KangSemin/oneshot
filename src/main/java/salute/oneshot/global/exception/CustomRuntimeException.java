package salute.oneshot.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import salute.oneshot.domain.common.dto.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public class CustomRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;
}
