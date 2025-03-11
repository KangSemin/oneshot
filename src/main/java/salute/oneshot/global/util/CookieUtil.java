package salute.oneshot.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CookieUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static Cookie getOrCreateCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("viewCount")).findAny();

        if (optionalCookie.isPresent()) {
            cookie = optionalCookie.get();
        }
        cookie = new Cookie("viewCookie", "[]");

        return cookie;
    }


    public static void ifNotExistSetValue(Cookie cookie, Long value){

        List<Long> valueList = getValues(cookie);
        boolean isExist = valueList.contains(value);

        if (!isExist) {
            valueList.add(value);
        }

        String stringValue = "";

        try {
            stringValue = objectMapper.writeValueAsString(valueList);
        } catch (JsonProcessingException e) {}

        cookie.setValue(stringValue);

    }

    private static List<Long> getValues(Cookie cookie) {

        List<Long> valueList = new ArrayList<>();
        String values = cookie.getValue();

        try {
            valueList = objectMapper.readValue(values, new TypeReference<List<Long>>() {});
        } catch (JsonProcessingException e) {}

        return valueList;
    }
}
