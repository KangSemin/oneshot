package salute.oneshot.domain.common.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final HttpStatus httpStatus;
    private final String errorMessage;

    public ErrorResponse(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.errorMessage = errorCode.getMessage();
    }
}
