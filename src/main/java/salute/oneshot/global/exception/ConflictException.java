package salute.oneshot.global.exception;

import salute.oneshot.domain.common.dto.error.ErrorCode;

public class ConflictException extends CustomRuntimeException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}