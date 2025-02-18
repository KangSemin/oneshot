package salute.oneshot.domain.payment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.boot.json.JsonParser;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.CustomRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PaymentErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        // TODO: 더 깔끔한 예외 처리 고민
        return new ResponseStatusException(HttpStatusCode.valueOf(response.status()), "methodKey: " + methodKey + ", response: " + response.toString());
    }
}
