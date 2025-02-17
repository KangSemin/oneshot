package salute.oneshot.domain.payment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.CustomRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class PaymentErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("s", s);
        errorData.put("response", response);

        try {
            String json = objectMapper.writeValueAsString(errorData);
            return new Exception(json);
        } catch (JsonProcessingException e) {
            return new Exception("Error serializing s and response", e);
        }
    }
}
