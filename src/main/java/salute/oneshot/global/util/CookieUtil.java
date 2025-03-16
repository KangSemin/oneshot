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


    public static Cookie getOrCreateCookie(HttpServletRequest request, String cookieName) {

        //해당 쿠키가 없으면 빈 문자열을 값으로 가진 쿠키를 반환한다

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return new Cookie(cookieName, "");
        }

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName)).findFirst();

        if (optionalCookie.isEmpty()) {
            return new Cookie(cookieName, "");
        }

        return optionalCookie.get();
    }

    public static boolean isExistValue(Cookie cookie, String cookieId) {

        String values = cookie.getValue();
        return values.contains(cookieId);

    }
}
