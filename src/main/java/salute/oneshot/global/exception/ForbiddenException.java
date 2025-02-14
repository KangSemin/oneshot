package salute.oneshot.global.exception;

import salute.oneshot.domain.common.dto.error.ErrorCode;

public class ForbiddenException extends CustomRuntimeException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
