package salute.oneshot.global.exception;

import salute.oneshot.domain.common.dto.error.ErrorCode;

public class UnauthorizedException extends CustomRuntimeException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
