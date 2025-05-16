package salute.oneshot.global.exception;

import salute.oneshot.domain.common.dto.error.ErrorCode;

public class InvalidException extends CustomRuntimeException {
    public InvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
