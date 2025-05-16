package salute.oneshot.global.exception;

import salute.oneshot.domain.common.dto.error.ErrorCode;

public class NotFoundException extends CustomRuntimeException {
    public NotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
