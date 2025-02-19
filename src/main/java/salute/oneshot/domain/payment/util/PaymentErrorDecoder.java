package salute.oneshot.domain.payment.util;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class PaymentErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        log.info(methodKey);

        String bodyContent = "";

        if (response.body() != null) {
            try (InputStream inputStream = response.body().asInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                bodyContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.info(bodyContent);

        // TODO: 더 깔끔한 예외 처리 고민
        return new ResponseStatusException(
                HttpStatusCode.valueOf(response.status()),
                "methodKey: " + methodKey + ", body: " + bodyContent
        );
    }
}
