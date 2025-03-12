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

        if (cookies == null) {
            return new Cookie(cookieName, "[]");
        }

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName)).findFirst();

        if (optionalCookie.isEmpty()) {
            return new Cookie(cookieName, "[]");
        }

        return optionalCookie.get();
    }

    public static boolean isExistValue(Cookie cookie, Long cocktailId) {

        return getValues(cookie).contains(cocktailId);

    }


    public static void SetValue(Cookie cookie, Long value) {

        List<Long> valueList = getValues(cookie);

        valueList.add(value);

        String stringValue = "";

        try {
            stringValue = objectMapper.writeValueAsString(valueList);
        } catch (JsonProcessingException e) {
        }

        cookie.setValue(stringValue);

    }

    public static List<Long> getValues(Cookie cookie) {


        String values = cookie.getValue();

        try {
           return objectMapper.readValue(values, new TypeReference<List<Long>>() {
            });

        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }


    }
}
